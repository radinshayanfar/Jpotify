package jpotify.model;

import helper.FileHelper;
import jpotify.model.Network.Server;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class User implements Serializable {

    private static final long serialVersionUID = 4879049040173323873L;
    private String name;

    private SongList library;
    private ArrayList<Playlist> playlists;
    private HashMap<String, Album> albums;
    private transient SongList currentList;
    private transient SongList currentSelectedListInGUI;
    private transient Playlist shuffledOriginal;
    private transient RecentlyPlayedPlaylist recentlyPlayed = new RecentlyPlayedPlaylist();
    private RepeatRule repeatRule = RepeatRule.OFF;
    private transient boolean shuffled = false;

    private static transient Server server;
    private transient ArrayList<RemoteClient> remoteClients;
    private transient ArrayList<NetworkPlaylist> othersSharedPlaylists = new ArrayList<>();
    private transient HashMap<RemoteClient, RecentlyPlayedPlaylist> othersRecentlyPlayed = new HashMap<>();

    {
        try {
            FileHelper.createDataDirectories();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            remoteClients = FileHelper.ipFileToArrayList("ip_list.txt");
        } catch (FileNotFoundException e) {
            remoteClients = new ArrayList<>();
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

    public ArrayList<RemoteClient> getRemoteClients() {
        if (remoteClients == null)
            try {
                remoteClients = FileHelper.ipFileToArrayList("ip_list.txt");
            } catch (FileNotFoundException e) {
                remoteClients = new ArrayList<>();
//            e.printStackTrace();
            }
        return remoteClients;
    }

    public ArrayList<String> getHosts() {
        if (remoteClients == null)
            try {
                remoteClients = FileHelper.ipFileToArrayList("ip_list.txt");
            } catch (FileNotFoundException e) {
                remoteClients = new ArrayList<>();
//            e.printStackTrace();
            }
        ArrayList<String> ret = new ArrayList<>();
        for (RemoteClient rc :
                remoteClients) {
            ret.add(rc.getHost());
        }
        return ret;
    }

    public RecentlyPlayedPlaylist getRecentlyPlayed() {
        if (recentlyPlayed == null)
            recentlyPlayed = new RecentlyPlayedPlaylist();
        return recentlyPlayed;
    }

    public ArrayList<RecentlyPlayedPlaylist> getOthersRecentlyPlayed() {
        if (othersRecentlyPlayed == null)
            othersRecentlyPlayed = new HashMap<>();
        return new ArrayList<>(othersRecentlyPlayed.values());
    }

    public void addOthersRecentlyPlayed(RemoteClient remoteClient, RecentlyPlayedPlaylist otherList) {
        if (otherList == null)
            return;
        if (othersRecentlyPlayed == null)
            othersRecentlyPlayed = new HashMap<>();
        othersRecentlyPlayed.put(remoteClient, otherList);
    }

    public Song playSong(int index) {
        if (currentList instanceof NetworkPlaylist) throw new Error("Don't use this method over network!");
        Song ret = currentList.songs.get(index);
        ret.updateLastPlayed();
//        if (getSharedPlaylist().getSongs().contains(ret)) {
//            getRecentlyPlayed().setCurrentSong(ret);
//            tellOthersAboutMyRecent();
//        }
        currentList.current = ret;
        return ret;
    }

    public Song playSongFromNetwork(int index, String host, int port) throws IOException {
        currentList.current = currentList.songs.get(index);
        return new Song(new File(FileHelper.downloadSongToTemporaryDirectory(host, port, index)));
    }

    public void playSong(Song song) {
        song.updateLastPlayed();
//        if (getSharedPlaylist().getSongs().contains(song)) {
//            getRecentlyPlayed().setCurrentSong(song);
//            tellOthersAboutMyRecent();
//        }
    }

    public void stopSong() {
        getRecentlyPlayed().removeCurrentSong();
//        tellOthersAboutMyRecent();
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

    public boolean removeSongFromLibrary(int songIndex) {
        if (getCurrentList().current  == getLibrarySongs().get(songIndex)) return false;
        Song song = getLibrarySongs().get(songIndex);
        for (int i = playlists.size() - 1; i >= 0; i--) {
            playlists.get(i).removeSong(song);
            if (playlists.get(i).getSongs().size() == 0)
                playlists.remove(i);
        }
        Album songAlbum = song.getAlbumReference();
        if (songAlbum != null) {
            if (songAlbum.songs.size() == 1)
                albums.remove(songAlbum.getName());
            else songAlbum.removeSong(song);
        }
        getLibrarySongs().remove(songIndex);
        return true;
    }

    public boolean removeSongFromPlaylist(int playlistIndex, int songIndex) {
        if (getCurrentList().current == playlists.get(playlistIndex).getSongs().get(songIndex)) return false;
        playlists.get(playlistIndex).removeSong(songIndex);
        return true;
    }

    public void changePlaylistName(int playlistIndex, String newName) {
        playlists.get(playlistIndex).setName(newName);
    }

    public List<Song> getLibrarySongs() {
        Collections.sort(library.songs);
        return library.songs;
    }

    public SongList getLibrary() {
        return library;
    }

    public List<Album> getAlbums() {
        ArrayList<Album> ret = new ArrayList<>(albums.values());
        Collections.sort(ret);
        return ret;
    }

    public int searchAlbums(String name){
        List<Album> albums = getAlbums();
        for (Album a: albums) {
            if (a.getName().equals(name))
                return albums.indexOf(a);
        }
        return -1;
    }

    private SongList getCurrentList() {
        if (currentList == null)
            currentList = new SongList();
        return currentList;
    }

    public SongList getCurrentSelectedListInGUI() {
        return currentSelectedListInGUI;
    }

    public Playlist newPlaylist(String name) {
        Playlist ret = new Playlist(name, true);
        playlists.add(ret);
        return ret;
    }

    public int newPlaylist(String name, ArrayList<Song> songs) {
        Playlist newList = new Playlist(name, songs);
        playlists.add(newList);
        return playlists.size() - 1;
    }

    public void addSharedPlaylist(NetworkPlaylist playlist) {
        if (playlist == null)
            return;
        int i = getOthersSharedPlaylists().indexOf(playlist);
        if (i == -1)
            othersSharedPlaylists.add(playlist);
        else
            othersSharedPlaylists.set(i, playlist);
    }

    public void removeSharedPlaylist(int index) {
        if (othersSharedPlaylists == null)
            othersSharedPlaylists = new ArrayList<>();
        othersSharedPlaylists.remove(index);
    }

    public void removeSharedPlaylist(NetworkPlaylist playlist) {
        if (othersSharedPlaylists == null)
            othersSharedPlaylists = new ArrayList<>();
        othersSharedPlaylists.remove(playlist);
    }

    public ArrayList<NetworkPlaylist> getOthersSharedPlaylists() {
        if (othersSharedPlaylists == null)
            othersSharedPlaylists = new ArrayList<>();
        return othersSharedPlaylists;
    }

    public void removePlaylist() {
        playlists.remove(getCurrentSelectedListInGUI());
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

//    Shuffle, Repeat, Next and Previous, Set current list

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
            shuffledOriginal = null;
        }
    }

    public boolean turnShuffleOn() {
        if (currentList instanceof Playlist) {
            shuffledOriginal = (Playlist) currentList;
            currentList = ((Playlist) currentList).getShuffled();
            return shuffled = true;
        }
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

//    Network methods

    public void startHttpServer(int port) {
        if (server == null) {
            try {
                server = new Server(this, port);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            server.changeUser(this);
        }
        startConnectionToOthers(port);
    }

    public void stopHttpServer() {
        server.stopServer();
    }

    private void tellOthersAboutMyRecent() {
        for (RemoteClient r :
                getRemoteClients()) {
            try {
                URL url = new URL("http://" + r.getHost() + ":" + r.getPort() + "/updateRecent");
                URLConnection connection = url.openConnection();
                connection.setDoOutput(true);
                ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());
                out.writeInt(r.getPort());
                out.writeObject(getRecentlyPlayed());
                out.flush();
                out.close();
                connection.getInputStream().close();
            } catch (IOException e) {
//                e.printStackTrace();
            }
        }
    }

    public void tellOthersAboutMyShared() {
        for (RemoteClient r :
                getRemoteClients()) {
            try {
                URL url = new URL("http://" + r.getHost() + ":" + r.getPort() + "/updatePlaylist");
                URLConnection connection = url.openConnection();
                connection.setDoOutput(true);
                ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());
                out.writeInt(r.getPort());
                out.writeObject(getSharedPlaylist());
                out.flush();
                out.close();
                connection.getInputStream().close();
            } catch (IOException e) {
//                e.printStackTrace();
            }
        }
    }

    private void startConnectionToOthers(int port) {
        for (RemoteClient r :
                getRemoteClients()) {
            try {
                URL url = new URL("http://" + r.getHost() + ":" + r.getPort() + "/connect");
                URLConnection connection = url.openConnection();
                connection.setDoOutput(true);
                ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());
                out.writeInt(port);
                out.writeObject(getSharedPlaylist());
                out.flush();
                out.close();
                ObjectInputStream in = new ObjectInputStream(connection.getInputStream());
                this.addSharedPlaylist(new NetworkPlaylist(((Playlist) in.readObject()).getSongs(), r.getHost(), r.getPort()));
                this.addOthersRecentlyPlayed(r, (RecentlyPlayedPlaylist) in.readObject());
                in.close();
            } catch (IOException | ClassNotFoundException e) {
//                e.printStackTrace();
            }
        }
    }
}
