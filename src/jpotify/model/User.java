package jpotify.model;

import helper.FileHelper;
import jpotify.model.Network.Server;

import java.io.*;
import java.util.ArrayList;

public class User {

    private String name;
    private Server server;

    {
        try {
            FileHelper.createDataDirectories();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<String> IPs;
    {
        try {
            IPs = FileHelper.fileToArrayList("ip_list.txt");
        } catch (FileNotFoundException e) {
            IPs = new ArrayList<>();
//            e.printStackTrace();
        }
    }

    private ArrayList<Song> library;
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

    private ArrayList<Playlist> playlists;
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

    private ArrayList<Album> albums;
    {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("data/albums.jdf"));
            albums = (ArrayList<Album>) in.readObject();
        } catch (IOException e) {
            albums = new ArrayList<>();
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

    public void stopHttpServer() {
        server.stopServer();
    }
}
