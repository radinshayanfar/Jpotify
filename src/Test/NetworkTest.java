package Test;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;
import jpotify.model.Song;
import jpotify.model.User;

import java.io.File;
import java.io.IOException;

public class NetworkTest {
    public static void main(String[] args) throws IOException, InterruptedException, UnsupportedTagException, InvalidDataException, JavaLayerException {
        User user = new User("Radin");

        user.getSharedPlaylist().addSong(new Song(new File("Ehsan-Khajehamiri-Ehsase-Aramesh.mp3")));
        user.getSharedPlaylist().addSong(new Song(new File("/media/radin/My Life...!/RADIN/آهنگ/تهدید.mp3")));

        Thread.sleep(120_000);

        user.stopHttpServer();
    }
}
