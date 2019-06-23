package Test;

import jpotify.model.RecentlyPlayedPlaylist;
import jpotify.model.Song;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.net.URLConnection;

public class NetworkRecentlyPlayedTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        URL url = new URL("http://localhost:3245/getRecentlyPlayedList");
        URLConnection connection = url.openConnection();
        ObjectInputStream in = new ObjectInputStream(connection.getInputStream());
        String name = (String) in.readObject();
        RecentlyPlayedPlaylist playlist = (RecentlyPlayedPlaylist) in.readObject();

        System.out.println(name);
        for (Song s :
                playlist.getSongs()) {
            System.out.println(s.getTitle());
        }
        System.out.println("Currently playing: " + playlist.getCurrentSong().getTitle());
    }
}
