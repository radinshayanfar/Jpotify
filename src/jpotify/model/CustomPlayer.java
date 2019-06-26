package jpotify.model;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.Player;
import jpotify.controller.MainController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CustomPlayer {

    private final static int NOTـSTARTED = 0;
    private final static int PLAYING = 1;
    private final static int PAUSED = 2;
    private final static int FINISHED = 3;

    // the player actually doing all the work
    private Player player;

    // song duration in milliseconds
    private final long duration;

    // song frames count
    private final long frameCount;

    // song file object
    final private File file;

    // locking object used to communicate with player thread
    private final Object playerLock = new Object();

    // status variable what player thread is doing/supposed to do
    private int playerStatus = NOTـSTARTED;

    // keeping position to invoke label updater
    private long lastInvokedTimePosition;
    private int lastSeekPosition = 0;

    // play volume
    private float gainVolume = 0.0f;
    private boolean volumeChanged = false;

    private MainController controller;

    public CustomPlayer(final File file, MainController controller) throws JavaLayerException, IOException, InvalidDataException, UnsupportedTagException {
        this(file, controller, null);
    }

    public CustomPlayer(final File file, MainController controller, final AudioDevice audioDevice) throws JavaLayerException, IOException, InvalidDataException, UnsupportedTagException {
        this.file = file;
        Mp3File mp3File = new Mp3File(file);
        this.controller = controller;
        this.duration = mp3File.getLengthInMilliseconds();
        this.frameCount = mp3File.getFrameCount();
        this.player = new Player(new FileInputStream(file), audioDevice);
    }

    /**
     * Starts playback (resumes if paused)
     */
    public void play() {
        synchronized (playerLock) {
            switch (playerStatus) {
                case NOTـSTARTED:
                    final Runnable r = this::playInternal;
                    final Thread t = new Thread(r);
                    t.setDaemon(true);
                    t.setPriority(Thread.MAX_PRIORITY);
                    playerStatus = PLAYING;
                    t.start();
                    break;
                case PAUSED:
                    resume();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Pauses playback. Returns true if new state is PAUSED.
     */
    public boolean pause() {
        synchronized (playerLock) {
            if (playerStatus == PLAYING) {
                playerStatus = PAUSED;
            }
            return playerStatus == PAUSED;
        }
    }

    /**
     * Resumes playback. Returns true if the new state is PLAYING.
     */
    public boolean resume() {
        synchronized (playerLock) {
            if (playerStatus == PAUSED) {
                playerStatus = PLAYING;
                playerLock.notifyAll();
            }
            return playerStatus == PLAYING;
        }
    }

    /**
     * Stops playback. If not playing, does nothing
     */
    public void stop() {
        synchronized (playerLock) {
            playerStatus = FINISHED;
            playerLock.notifyAll();
        }
    }

    private void playInternal() {
        lastInvokedTimePosition = 0;
        while (playerStatus != FINISHED) {
            try {
                if (!player.play(1)) {
                    break;
                }
            } catch (final JavaLayerException e) {
                break;
            }
            // check if paused or terminated
            synchronized (playerLock) {
                while (playerStatus == PAUSED) {
                    try {
                        playerLock.wait();
                    } catch (final InterruptedException e) {
                        // terminate player
                        break;
                    }
                }
            }
            if (volumeChanged) {
                player.setVolume(gainVolume);
                volumeChanged = false;
            }
            int position = lastSeekPosition + player.getPosition();
            if (position - lastInvokedTimePosition >= 1000) {
//                System.out.println(position);
                controller.updateJSlider((int) ((double)position / duration * 100));
                lastInvokedTimePosition = position;
//                System.err.println(lastInvokedTimePosition);
            }

        }
//        TODO: invoke song finished method
        close();
    }

    /**
     * Closes the player, regardless of current state.
     */
    public void close() {
        synchronized (playerLock) {
            playerStatus = FINISHED;
        }
        try {
            player.close();
        } catch (final Exception e) {
            // ignore, we are terminating anyway
        }
    }

    private void changePosition(int milliSecond) throws JavaLayerException {
        boolean wasPlaying = false;
        if (this.playerStatus == PLAYING) {
            wasPlaying = true;
            this.pause();
        }
        try {
            this.player = new Player(new FileInputStream(this.file));
        } catch (JavaLayerException | FileNotFoundException ignored) {
        }
        player.skipMilliSeconds(milliSecond);
        player.setVolume(gainVolume);
        if (wasPlaying)
            this.play();
    }

    public void changePositionHundred(int value) throws JavaLayerException {
        int milliSecond = (int) ((double) value / 100 * duration);
        changePosition(milliSecond);
//        positionPlayed = lastSeekPosition + player.getPosition();
        lastInvokedTimePosition = 0;
        lastSeekPosition = milliSecond;
    }

    public void setVolume(float volume) {
        this.gainVolume = volume;
        volumeChanged = true;
    }

}