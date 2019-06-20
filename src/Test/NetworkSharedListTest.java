package Test;

import jpotify.model.Playlist;
import jpotify.model.Song;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.net.URLConnection;

public class NetworkSharedListTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        URL url = new URL("http://localhost:3245/getRecentlyPlayedList");
        URLConnection connection = url.openConnection();
        ObjectInputStream in = new ObjectInputStream(connection.getInputStream());
        Playlist playlist = (Playlist) in.readObject();

        for (Song s :
                playlist.getSongs()) {
            System.out.println(s.getAddress().getAbsolutePath());
        }
    }
}
