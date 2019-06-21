package jpotify.model;

import helper.FileHelper;
import jpotify.model.Network.Server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class User {

    private String name;
    private Server server;
    private transient RecentlyPlayedPlaylist recentlyPlayed = new RecentlyPlayedPlaylist(false);
    private ArrayList<String> IPs;
    private ArrayList<Song> library;
    private ArrayList<Playlist> playlists;
    private HashMap<String, Album> albums;

    {
        try {
            FileHelper.createDataDirectories();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    {
        try {
            IPs = FileHelper.fileToArrayList("ip_list.txt");
        } catch (FileNotFoundException e) {
            IPs = new ArrayList<>();
//            e.printStackTrace();
        }
    }

    {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("data/lib.jdf"));
            library = (ArrayList<Song>) in.readObject();
        } catch (IOException e) {
            library = new ArrayList<>();
//            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("data/playlists.jdf"));
            playlists = (ArrayList<Playlist>) in.readObject();
        } catch (IOException e) {
            playlists = new ArrayList<>();
            playlists.add(new Playlist("Favorites", false));
            playlists.add(new Playlist("Shared", false));
//            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("data/albums.jdf"));
            albums = (HashMap<String, Album>) in.readObject();
        } catch (IOException e) {
            albums = new HashMap<>();
//            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public User(String name) {
        this.name = name;

        try {
            server = new Server(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Playlist getSharedPlaylist() {
        return playlists.get(1);
    }

    public ArrayList<String> getIPs() {
        return IPs;
    }

    public RecentlyPlayedPlaylist getRecentlyPlayed() {
        if (recentlyPlayed == null)
            recentlyPlayed = new RecentlyPlayedPlaylist(false);
        return recentlyPlayed;
    }

    public void stopHttpServer() {
        server.stopServer();
    }

    public Song playSong(int index) {
        Song ret = library.get(index);
        ret.updateLastPlayed();
        recentlyPlayed.setCurrentSong(ret);
        return ret;
    }

    public void stopSong() {
        recentlyPlayed.removeCurrentSong();
    }

    public void addSong(Song song) {
        if (library.contains(song)) return;
        library.add(song);
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

    public ArrayList<Song> getLibrary() {
        Collections.sort(library);
        return library;
    }

    public Collection<Album> getAlbums() {
        return albums.values();
    }

    public Playlist newPlaylist(String name) {
        Playlist ret = new Playlist(name, true);
        playlists.add(ret);
        return ret;
    }

    public void removePlaylist() {
//        TODO: implement method
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }
}
