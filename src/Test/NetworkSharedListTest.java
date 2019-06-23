package Test;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import helper.FileHelper;
import javazoom.jl.decoder.JavaLayerException;
import jpotify.model.CustomPlayer;
import jpotify.model.Playlist;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.net.URLConnection;

public class NetworkSharedListTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InvalidDataException, UnsupportedTagException, JavaLayerException, InterruptedException {
        URL url = new URL("http://localhost:3245/getSharedList");
        URLConnection connection = url.openConnection();
        ObjectInputStream in = new ObjectInputStream(connection.getInputStream());
        String name = (String) in.readObject();
        Playlist playlist = (Playlist) in.readObject();

        System.out.println(name);
        for (int i = 0; i < playlist.getSongs().size(); i++) {
            String fileName = FileHelper.downloadSongToTemporaryDirectory("localhost", 3245, i);

            CustomPlayer cs = new CustomPlayer(new File(fileName));
            cs.setVolume(-10f);
            cs.changePosition(50);
            cs.play();
            Thread.sleep(10_000);
            cs.stop();

        }
        FileHelper.deleteTemporaryDirectory();
    }
}
