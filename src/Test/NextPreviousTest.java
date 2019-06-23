package Test;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import helper.FileHelper;
import javazoom.jl.decoder.JavaLayerException;
import jpotify.model.*;

import java.io.IOException;

public class NextPreviousTest {
    public static void main(String[] args) throws IOException, UnsupportedTagException, InvalidDataException, JavaLayerException, InterruptedException {
        User user;
        try {
            user = FileHelper.loadUsers().getUsers().get(0);
        } catch (IOException | ClassNotFoundException e) {
            user = new User("Maryam");
        }

        user.setCurrentSelectedListInGUI(user.getPlaylists().get(2));
        for (Song s :
                user.getPlaylists().get(2).getSongs()) {
            System.out.println(s.getTitle());
        }

        user.setCurrentList();
//        user.turnShuffleOn();

        user.setRepeatRule(RepeatRule.REPEAT);
        if(user.isShuffled())
            user.shuffleCurrentSelected();
//        user.unshuffleCurrentSelected();

        {
            user.playSong(2);
            CustomPlayer cs = new CustomPlayer(user.getPlaylists().get(2).getSongs().get(2).getAddress());
            cs.play();
            Thread.sleep(10_000);
            cs.stop();
            Song next = user.next();
            user.playSong(next);
            cs = new CustomPlayer(next.getAddress());
            cs.play();
            Thread.sleep(10_000);
            cs.stop();
        }
//        for (int i = 0; i < 5; i++) {
//            Song song = user.next();
//            user.playSong(song);
//            CustomPlayer cs = new CustomPlayer(song.getAddress());
//            cs.play();
//            Thread.sleep(10_000);
//            cs.stop();
//        }

        System.out.println("-----------");
        for (Song s :
                user.getRecentlyPlayed().getSongs()) {
            System.out.println(s.getTitle());
        }

        System.out.println("-----------");
        for (Song s :
                user.getLibrary()) {
            System.out.println(s.getTitle());
        }

        System.out.println("-----------");
        for (Album a:
                user.getAlbums()) {
            System.out.println(a.getName());
        }
        user.setCurrentSelectedListInGUI(user.getAlbums().get(0));
        user.setCurrentList();
        for (int i = 0; i < 5; i++) {
            Song song = user.forceNext();
            user.playSong(song);
            CustomPlayer cs = new CustomPlayer(song.getAddress());
            cs.play();
            Thread.sleep(5_000);
            cs.stop();
        }
    }
}
