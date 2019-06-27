package jpotify.controller;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import helper.FileHelper;
import javazoom.jl.decoder.JavaLayerException;
import jpotify.model.*;
import jpotify.view.MainView;
import jpotify.view.centerpanel.JAlbum;
import jpotify.view.centerpanel.JPlaylistSong;
import jpotify.view.centerpanel.JSong;
import jpotify.view.leftpanel.CreateNewPlaylist;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainController {
    public static final int JALBUM = 3,PLAYLIST = 2, MYSONG = 0, ALBUMS = 1, BLANPAGE = 4;
    //    public static final int PLAY_BUTTON=1;
    private MainView mainView;
    private Users users;
    private User user;
    private CustomPlayer player;
    private int volume = 0;
    private int currentMode;
    private int saveIndex;

    public MainController(Users users, int userIndex) {
        this.users = users;
        this.user = users.getUser(userIndex);
        user.setRepeatRule(RepeatRule.OFF);
//        user.turnShuffleOn();
        mainView = new MainView(this);
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

    public void setCurrentMode(int mode, int index){
        this.currentMode = mode;
        switch (mode){
            case JALBUM:
                user.setCurrentSelectedListInGUI(user.getAlbums().get(index));
                break;
            case MYSONG:
                user.setCurrentSelectedListInGUI(user.getLibrary());
                break;
            case PLAYLIST:
                user.setCurrentSelectedListInGUI(user.getPlaylists().get(index));
                break;
        }
    }

    public int getCurrentMode(){
        return currentMode;
    }

    public void playSelectedSong(int index) {
        user.setCurrentList();
        Song song = user.playSong(index);
        playSongWithCustomPlayer(song);
        GUIChangeForSongPlay(song);
    }

    private void GUIChangeForSongPlay(Song song) {
        mainView.changeArtwork(song.getArtwork());
        mainView.getBottomPanelView().getSongInfoLabel().setSongInfo(song.getTitle(), song.getArtist(), song.getAlbum());
        mainView.getBottomPanelView().getControlPanel().changePlayButton(false);
    }

    private void GUIChangeForSongStop() {
        try {
            mainView.changeArtwork(FileHelper.loadSampleArtwork());
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainView.getBottomPanelView().getSongInfoLabel().setSongInfo("", "", "");
        mainView.getBottomPanelView().getControlPanel().changePlayButton(true);
        updateJSlider(0);
    }

    public void updateJSlider(int state) {
        mainView.getBottomPanelView().getControlPanel().getControlBar().setValue(state);
        mainView.getBottomPanelView().getControlPanel().revalidate();
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

    public void changeCenterPanel(int mode, int index) {
        mainView.getCenterPanelView().displayPanel(mode, index);
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

    public void deleteSongFromLibrary(int index) {
        if (user.removeSongFromLibrary(index)) {
            changeCenterPanel(MYSONG, 0);
        }
    }

    public ArrayList<JAlbum> getJAlbum() throws IOException {
        List<Album> albums = user.getAlbums();
        ArrayList<JAlbum> jAlbums = new ArrayList<>();
        for (int i = 0; i < albums.size(); i++) {
            jAlbums.add(new JAlbum(this, albums.get(i).getName(), albums.get(i).getTitles() ,albums.get(i).getArtwork(), i));
        }
        return jAlbums;
    }

    public ArrayList<JPlaylistSong> getJPlaylistSong(int index) {
        ArrayList<JPlaylistSong> ret = new ArrayList<>();
        int i = 0;
        for (Song s: user.getPlaylists().get(index).getSongs()) {
            ret.add(new JPlaylistSong(this,index,i++,s.getTitle(),s.getArtist(),s.getAlbum()));
        }
        return ret;
    }

    public ArrayList<JSong> getJSongs(int mode, int index) throws IOException {
        ArrayList<JSong> jSongs = new ArrayList<>();
        List<Song> songs = null;
        switch (mode){
            case MYSONG:
                songs = user.getLibrarySongs();
                break;
            case JALBUM:
                songs = user.getAlbums().get(index).getSongs();
                break;
//            case PLAYLIST:
//                songs = user.getPlaylists().get(index).getSongs();
//                break;
        }
        for (int i = 0; i < songs.size(); i++) {
            jSongs.add(new JSong(this, songs.get(i).getTitle(), songs.get(i).getArtist(), songs.get(i).getAlbum(), songs.get(i).getArtwork(), i));
        }
        System.out.println(jSongs.get(0).getTitle().getText());
        return jSongs;
    }

    public int searchAlbumIndex(String text) {
        return user.searchAlbums(text);
    }

    public void createNewPlaylistFrame() {
        ArrayList<String> songNames = getLibrarySongsNames();
        CreateNewPlaylist createNewPlaylist = new CreateNewPlaylist(this, songNames);
        createNewPlaylist.setVisible(true);
    }

    public ArrayList<String> getLibrarySongsNames() {
        ArrayList<String> songNames = new ArrayList<>();
        for (Song s: user.getLibrarySongs())
            songNames.add(s.getTitle());
        return songNames;
    }

    public void createNewPlaylist(String text, ArrayList<Integer> indexes) {
        ArrayList<Song> songs = new ArrayList<>();
        for (Integer i: indexes) {
            songs.add(user.getLibrarySongs().get(i));
        }
        int lastIndex = user.newPlaylist(text, songs);
        mainView.getLeftPanelView().getPlaylistBar().refreshList(lastIndex, text);
        changeCenterPanel(PLAYLIST, lastIndex);
////        for (Playlist p :
////                user.getPlaylists()) {
////            System.out.println(p.getName() + ": " + p.getSongs());
////        }
    }

    public String getPlayListName(int index) {
        return user.getPlaylists().get(index).getName();
    }

    public HashMap<Integer, String> getPlayLists() {
        HashMap<Integer, String> ret = new HashMap<>();
        int i = 0;
        for (Playlist p: user.getPlaylists()) {
            System.out.println(p.getName());
            ret.put(i++,p.getName());
        }
        return ret;
    }

    public void addSongToPlaylist(ArrayList<Integer> indexes) {
//        ArrayList<Song> songs = new ArrayList<>();
        int index = user.getPlaylists().indexOf(user.getCurrentSelectedListInGUI());
        for (Integer i: indexes) {
//            songs.add(user.getLibrarySongs().get(i));
            user.getPlaylists().get(index).addSong(user.getLibrarySongs().get(i));
        }
//        int lastIndex = user.newPlaylist(text, songs);
//        mainView.getLeftPanelView().getPlaylistBar().refreshList();
//        mainView.getLeftPanelView().getPlaylistBar().getPlayLists().setSelectedIndex(2);
        mainView.getLeftPanelView().getPlaylistBar().revalidate();
//        mainView.getCenterPanelView().displayPanel(PLAYLIST, index);
        changeCenterPanel(PLAYLIST, index);
    }

    public void removePlaylist() {
        user.removePlaylist();
        System.out.println("T1");
//        TODO tell him to check
        player.stop();
        user.setCurrentSelectedListInGUI(null);
//
        mainView.getCenterPanelView().displayPanel(BLANPAGE, 0);
        mainView.getLeftPanelView().refreshPlaylistBar();
    }

    public void setRepeat(RepeatRule repeatRule) {
        //TODO Set repeat
    }

    public void turnShuffleOff() {
        //TODO turn shuffle off
    }

    public boolean turnShuffleOn() {
        //TOdo turn Shuffle on
        return false;
    }
}
