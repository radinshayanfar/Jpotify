package jpotify.model;

import helper.FileHelper;
import helper.StringHelper;
import jpotify.model.Network.Server;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class User implements Serializable {

    private static final long serialVersionUID = 4879049040173323873L;
    private String name;
    private static transient Server server;
    private transient RecentlyPlayedPlaylist recentlyPlayed = new RecentlyPlayedPlaylist();
    private transient ArrayList<RemoteClient> remoteClients;
    private SongList library;
    private ArrayList<Playlist> playlists;
    private HashMap<String, Album> albums;
    private transient SongList currentList;
    private transient SongList currentSelectedListInGUI;
    private transient Playlist shuffledOriginal;
    private RepeatRule repeatRule = RepeatRule.OFF;
    private transient boolean shuffled = false;
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
        othersRecentlyPlayed.put(remoteClient, otherList);
    }

    public Song playSong(int index) {
        if (currentList instanceof NetworkPlaylist) throw new Error("Don't use this method over network!");
        Song ret = currentList.songs.get(index);
        ret.updateLastPlayed();
        if (getSharedPlaylist().getSongs().contains(ret))
            getRecentlyPlayed().setCurrentSong(ret);
        currentList.current = ret;
        return ret;
    }

    public Song playSongFromNetwork(int index, String host, int port) throws IOException {
        currentList.current = currentList.songs.get(index);
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
//        TODO: implement method
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
        startConnectionToOthers();
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
                ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());
                out.writeInt(r.getPort());
                out.writeObject(getRecentlyPlayed());
            } catch (IOException e) {
//                e.printStackTrace();
            }
        }
    }

    private void tellOthersAboutMyShared() {
        for (RemoteClient r :
                getRemoteClients()) {
            try {
                URL url = new URL("http://" + r.getHost() + ":" + r.getPort() + "/updatePlaylist");
                URLConnection connection = url.openConnection();
                ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());
                out.writeInt(r.getPort());
                out.writeObject(playlists.get(1));
            } catch (IOException e) {
//                e.printStackTrace();
            }
        }
    }

    private void startConnectionToOthers() {
        for (RemoteClient r :
                getRemoteClients()) {
            try {
                URL url = new URL("http://" + r.getHost() + ":" + r.getPort() + "/connect");
                URLConnection connection = url.openConnection();
                ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());
                out.writeInt(r.getPort());
                out.writeObject(getRecentlyPlayed());
                ObjectInputStream in = new ObjectInputStream(connection.getInputStream());
                this.addSharedPlaylist((NetworkPlaylist) in.readObject());
                this.addOthersRecentlyPlayed(r, (RecentlyPlayedPlaylist) in.readObject());
            } catch (IOException | ClassNotFoundException e) {
//                e.printStackTrace();
            }
        }
    }
}
