package jpotify.model;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.Player;

import java.io.*;

public class CustomPlayer {

    private final static int NOT_STARTED = 0;
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
    private int playerStatus = NOT_STARTED;

    // keeping position to invoke label updater
    private long lastInvokedTimePosition;

    public CustomPlayer(final File file) throws JavaLayerException, IOException, InvalidDataException, UnsupportedTagException {
        this(file, null);
    }

    public CustomPlayer(final File file, final AudioDevice audioDevice) throws JavaLayerException, IOException, InvalidDataException, UnsupportedTagException {
        this.file = file;
        Mp3File mp3File = new Mp3File(file);
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
                case NOT_STARTED:
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
            int position = player.getPosition();
            if (position - lastInvokedTimePosition >= 1000) {
//                TODO: invoke label updater
                lastInvokedTimePosition = position;
//                System.err.println(lastInvokedTimePosition);
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

    public void changePosition(int second) throws JavaLayerException {
        this.pause();
        try {
            this.player = new Player(new FileInputStream(this.file));
        } catch (JavaLayerException | FileNotFoundException ignored) {
        }
        player.skipMilliSeconds(second * 1000);
        this.play();
    }
}