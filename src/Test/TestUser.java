package Test;

import helper.FileHelper;
import jpotify.model.*;

import java.io.File;
import java.io.IOException;

public class TestUser {
    public static void main(String[] args) throws IOException, InterruptedException {
        User user;
        try {
            user = FileHelper.loadUsers().getUsers().get(0);
        } catch (IOException | ClassNotFoundException e) {
            user = new User("Maryam");
        }

        Song s0 = new Song(new File("/home/radin/Downloads/musics/Babak-Jahanbakhsh-Sheydaei.mp3"));
        Song s1 = new Song(new File("/home/radin/Downloads/musics/Bijan-Mortazavi-Lavand.mp3"));
        Song s2 = new Song(new File("/home/radin/Downloads/musics/Ehsan-Khajehamiri-Ehsase-Aramesh.mp3"));
        Song s3 = new Song(new File("/home/radin/Downloads/musics/Mansour-Zendegi-Chist.mp3"));
        Song s4 = new Song(new File("/home/radin/Downloads/musics/Moein-Sharab.mp3"));
        Song s5 = new Song(new File("/home/radin/Downloads/musics/Moein-Jodaee.mp3"));
        Song s6 = new Song(new File("/home/radin/Downloads/musics/Moein-Shoghe-Safar.mp3"));
        Song s7 = new Song(new File("/home/radin/Downloads/musics/06 Bahaneh.mp3"));
        Song s8 = new Song(new File("/home/radin/Downloads/musics/Moein-Vaghti-To-Ba-Man-Nisti.mp3"));
        user.addSong(s0);
        user.addSong(s1);
        user.addSong(s2);
        user.addSong(s3);
        user.addSong(s4);
        user.addSong(s5);
        user.addSong(s6);
        user.addSong(s7);
        user.addSong(s8);

        user.playSong(5);
        Thread.sleep(10);
        user.playSong(2);
        Thread.sleep(10);
        user.playSong(4);
        Thread.sleep(10);

        for (Album a :
                user.getAlbums()) {
//            System.out.println(s.getAlbumReference());
            System.out.println(a.getName());
        }
        System.out.println(user.getRecentlyPlayed().getCurrentSong().getTitle());
//        user.stopSong();
        System.out.println(user.getRecentlyPlayed().getCurrentSong());

        System.out.println("----------------");

        Playlist playlist = user.newPlaylist("Best of Moein");
        playlist.addSong(s4); // 0 sharab
        playlist.addSong(s6); // 1 shoghe safar
        playlist.addSong(s7); // 2 bahaneh
        playlist.addSong(s5); // 3 jodaee

        playlist.moveDown(3);
        playlist.moveUp(0);
        playlist.moveDown(0);
        playlist.moveUp(3);

        for (Song s :
                playlist.getSongs()) {
            System.out.println(s.getTitle());
        }

        for (Playlist p :
                user.getPlaylists()) {
            System.out.println(p.getName());
        }

        user.getSharedPlaylist().addSong(s1);
        user.getSharedPlaylist().addSong(s2);
        user.getSharedPlaylist().addSong(s3);
        user.getSharedPlaylist().addSong(s4);
        user.getSharedPlaylist().addSong(s5);
        user.getSharedPlaylist().addSong(s6);
        user.getSharedPlaylist().addSong(s7);
        user.getSharedPlaylist().moveUp(3);
        user.getSharedPlaylist().moveUp(2);
        user.getSharedPlaylist().moveUp(1);
        user.getSharedPlaylist().moveUp(0);

//        Users users = new Users();
//        users.addUser(user);
//        FileHelper.saveUsers(users);

        user.startHttpServer();
        Thread.sleep(1200_000);
        user.stopHttpServer();
    }
}
