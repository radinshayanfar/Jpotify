package jpotify.controller;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import helper.FileHelper;
import javazoom.jl.decoder.JavaLayerException;
import jpotify.model.*;
import jpotify.view.MainView;
import jpotify.view.centerpanel.JSong;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainController {
    public static final int PLAYLIST = 2, MYSONG = 0, ALBUMS = 1;
    //    public static final int PLAY_BUTTON=1;
    private MainView mainView;
    private Users users;
    private User user;
    private CustomPlayer player;
    private int volume = 0;

    public MainController(Users users, int userIndex) {
        mainView = new MainView(this);
        this.users = users;
        this.user = users.getUser(userIndex);
        user.setRepeatRule(RepeatRule.REPEAT);
        user.turnShuffleOn();
    }

    public void addSongToLibrary(File... files) {
        for (File f :
                files) {
            try {
                user.addSong(new Song(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<JSong> getJSongs() throws IOException {
        ArrayList<JSong> jSongs = new ArrayList<>();
        List<Song> songs = user.getLibrarySongs();
//        System.out.println(songs);
        for (int i = 0; i < songs.size(); i++) {
            jSongs.add(new JSong(this, songs.get(i).getTitle(), songs.get(i).getArtist(), songs.get(i).getAlbum(), songs.get(i).getArtwork(), i));
        }
        return jSongs;
    }

    public void mySongIsOn() {
        user.setCurrentSelectedListInGUI(user.getLibrary());
    }

    public void playSelectedSong(int index) {
        user.setCurrentList();
        Song song = user.playSong(index);
        playSongWithCustomPlayer(song);
        GUIChangeForSongPlay(song);
    }

    private void GUIChangeForSongPlay(Song song) {
        mainView.changeArtwork(song.getArtwork());
        mainView.getBottomPanelView().getControlPanel().changePlayButton(false);
    }

    private void GUIChangeForSongStop() {
//        try {
//            mainView.changeArtwork(FileHelper.loadSampleArtwork());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        TODO: remove artwork and bottom info
        mainView.getBottomPanelView().getControlPanel().changePlayButton(true);
        updateJSlider(0);
    }

    public void updateJSlider(int state) {
        mainView.getBottomPanelView().getControlPanel().getControlBar().setValue(state);
        mainView.getBottomPanelView().getControlPanel().revalidate();
    }

    public void changeCenterPanel(int mode) {
        switch (mode) {
            case ALBUMS:
//                mainView.getCenterPanelView().displayPanel(mode);
            case MYSONG:
                mainView.getCenterPanelView().displayPanel(mode, null);
            case PLAYLIST:
//                mainView.getCenterPanelView().displayPanel(mode);
        }
    }

//    public void controlButtonHandler(int mode) {
//        switch (mode) {
//            case PLAY_BUTTON:
//                mainView.getBottomPanelView().getControlPanel().changeButton(mode);
//        }
//    }

    public boolean pause(boolean pause) {
        if (player == null) return false;
        if (pause) player.pause();
        else player.play();
        return true;
    }

    public void pausePlayerForSeek() {
        if (player != null)
            player.pause();
    }

    public void resumePlayerForSeek() {
        if (player != null && mainView.getBottomPanelView().getControlPanel().isPlaying())
            player.resume();
    }

    public boolean songSliderChanged(int value) {
        if (player == null) return false;
        try {
            player.changePositionHundred(value);
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void songReachedEnd() {
        user.stopSong();
        Song nextSong = user.next();
        if (nextSong == null) {
            songListReachedEnd();
            return;
        }
        user.playSong(nextSong);
        playSongWithCustomPlayer(nextSong);
        GUIChangeForSongPlay(nextSong);
    }

    public void nextPressed() {
        if (player == null) return;
        user.stopSong();
        Song nextSong = user.forceNext();
        if (nextSong == null) {
            songListReachedEnd();
            return;
        }
        user.playSong(nextSong);
        playSongWithCustomPlayer(nextSong);
        GUIChangeForSongPlay(nextSong);
    }

    public void previousPressed() {
        if (player == null) return;
        user.stopSong();
        Song previousSong = user.previous();
        if (previousSong == null) {
            songListReachedEnd();
            return;
        }
        user.playSong(previousSong);
        playSongWithCustomPlayer(previousSong);
        GUIChangeForSongPlay(previousSong);
    }

    private void songListReachedEnd() {
        if (player != null) {
            player.stop();
            player = null;
        }
        GUIChangeForSongStop();
    }

    private void playSongWithCustomPlayer(Song song) {
        try {
            if (player != null)
                player.stop();
            player = new CustomPlayer(song.getAddress(), this);
            player.setVolume(volume);
            player.play();
        } catch (JavaLayerException | IOException | InvalidDataException | UnsupportedTagException e) {
            e.printStackTrace();
        }
    }

    public void changeCenterPanel(int mode, ArrayList information) {
        switch (mode) {
            case ALBUMS:
                mainView.getCenterPanelView().displayPanel(mode, information);
            case MYSONG:
                mainView.getCenterPanelView().displayPanel(mode, information);
            case PLAYLIST:
                mainView.getCenterPanelView().displayPanel(mode, information);
        }
    }

    public void saveState() {
//        Users users = new Users();
//        users.addUser(user);
        try {
            FileHelper.saveUsers(this.users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeVolume(int value) {
        volume = value;
        if (player != null)
            player.setVolume(value);
    }

}
