package jpotify.model;

import helper.FileHelper;
import jpotify.model.Network.Server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class User implements Serializable {

    private static final long serialVersionUID = 4879049040173323873L;
    private String name;
    private static transient Server server;
    private transient RecentlyPlayedPlaylist recentlyPlayed = new RecentlyPlayedPlaylist();
    private transient ArrayList<String> IPs;
    private SongList library;
    private ArrayList<Playlist> playlists;
    private ArrayList<Playlist> sharedPlaylists;
    private HashMap<String, Album> albums;
    private transient SongList currentList;
    private transient SongList currentSelectedListInGUI;
    private transient Playlist shuffledOriginal;
    private RepeatRule repeatRule = RepeatRule.OFF;
    private transient boolean shuffled = false;

    {
        try {
            FileHelper.createDataDirectories();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            IPs = FileHelper.fileToArrayList("ip_list.txt");
        } catch (FileNotFoundException e) {
            IPs = new ArrayList<>();
//            e.printStackTrace();
        }

        library = new SongList();

        playlists = new ArrayList<>();
        playlists.add(new Playlist("Favorites", false));
        playlists.add(new Playlist("Shared", false));

        albums = new HashMap<>();
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Playlist getSharedPlaylist() {
        return playlists.get(1);
    }

    public ArrayList<String> getIPs() {
        if (IPs == null)
            try {
                IPs = FileHelper.fileToArrayList("ip_list.txt");
            } catch (FileNotFoundException e) {
                IPs = new ArrayList<>();
//            e.printStackTrace();
            }
        return IPs;
    }

    public RecentlyPlayedPlaylist getRecentlyPlayed() {
        if (recentlyPlayed == null)
            recentlyPlayed = new RecentlyPlayedPlaylist();
        return recentlyPlayed;
    }

    public void startHttpServer() {
        if (server == null) {
            try {
                server = new Server(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            server.changeUser(this);
        }
    }

    public void stopHttpServer() {
        server.stopServer();
    }

    public Song playSong(int index) {
        Song ret = library.songs.get(index);
        ret.updateLastPlayed();
        getRecentlyPlayed().setCurrentSong(ret);
        return ret;
    }

    public Song playSongFromNetwork(int index, String host, int port) throws IOException {
        return new Song(new File(FileHelper.downloadSongToTemporaryDirectory(host, port, index)));
    }

    public void playSong(Song song) {
        song.updateLastPlayed();
        if (getSharedPlaylist().getSongs().contains(song))
            getRecentlyPlayed().setCurrentSong(song);
    }

    public void stopSong() {
        getRecentlyPlayed().removeCurrentSong();
    }

    public void addSong(Song song) {
        if (library.songs.contains(song)) return;
        library.songs.add(song);
        String album = song.getAlbum();
        if (album == null) return;
        Album albumReference;
        if (albums.containsKey(album)) {
            albumReference = albums.get(album);
            albumReference.addSong(song);
        } else {
            albumReference = new Album(song);
            albums.put(album, albumReference);
        }
        song.setAlbumReference(albumReference);
    }

    public void removeSong() {
//        TODO: implement method
    }

    public List<Song> getLibrary() {
        Collections.sort(library.songs);
        return library.songs;
    }

    public List<Album> getAlbums() {
        return new ArrayList<>(albums.values());
    }

    public Playlist newPlaylist(String name) {
        Playlist ret = new Playlist(name, true);
        playlists.add(ret);
        return ret;
    }

    public void addSharedPlaylist(Playlist playlist) {
        sharedPlaylists.add(playlist);
    }

    public void removeSharedPlaylist(int index) {
        sharedPlaylists.remove(index);
    }

    public ArrayList<Playlist> getSharedPlaylists() {
        return sharedPlaylists;
    }

    public void removePlaylist() {
//        TODO: implement method
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void setCurrentSelectedListInGUI(SongList currentSelectedListInGUI) {
        this.currentSelectedListInGUI = currentSelectedListInGUI;
    }

    public void setCurrentList() {
        currentList = currentSelectedListInGUI;
    }

    public void shuffleCurrentSelected() {
        if (currentList instanceof Playlist) {
            shuffledOriginal = (Playlist) currentList;
            currentList = ((Playlist) currentList).getShuffled();
        }
    }

    public void unshuffleCurrentSelected() {
        if (currentList instanceof Playlist && shuffledOriginal != null) {
            currentList = shuffledOriginal;
        }
    }

    public boolean turnShuffleOn() {
        if (currentList instanceof Playlist)
            return shuffled = true;
        return false;
    }

    public void turnShuffleOff() {
        shuffled = false;
    }

    public boolean isShuffled() {
        return shuffled;
    }

    public void setRepeatRule(RepeatRule repeatRule) {
        this.repeatRule = repeatRule;
    }

    public Song next() {
        return currentList.next(repeatRule);
    }

    public Song forceNext() {
        return currentList.next(repeatRule == RepeatRule.REPEAT_ONE ? RepeatRule.REPEAT : repeatRule);
    }

    public Song previous() {
        return currentList.previous(repeatRule);
    }
}
