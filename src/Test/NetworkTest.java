package Test;

import jpotify.model.Song;
import jpotify.model.User;

import java.io.File;
import java.io.FileNotFoundException;

public class NetworkTest {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        User user = new User("Radin");

        user.getSharedPlaylist().addSong(new Song(new File("Ehsan-Khajehamiri-Ehsase-Aramesh.mp3")));

        Thread.sleep(60_000);

        user.stopHttpServer();
    }
}
