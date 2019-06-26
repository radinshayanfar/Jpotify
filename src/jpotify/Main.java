package jpotify;

import helper.FileHelper;
import jpotify.controller.MainController;
import jpotify.model.User;
import jpotify.model.Users;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Users users;
        try {
            users = FileHelper.loadUsers();
        } catch (IOException | ClassNotFoundException e) {
            users = new Users();
            users.addUser(new User("Radin"));
        }
        new MainController(users, 0);




        ArrayList<String> s = new ArrayList<>();
        s.add("dsf");
        s.add("dsfsdf");
        s.add("sdfjksdklfj");
        s.add("sdf");
        s.add("sdfdsf");
        s.add("dsf");
        s.add("dsfsdf");
        s.add("sdfjksdklfj");
        s.add("sdf");
        s.add("sdfdsf");
        s.add("dsf");
        s.add("dsfsdf");
        s.add("sdfjksdklfj");
        s.add("sdf");
        s.add("sdfdsf");
        s.add("dsf");
        s.add("dsfsdf");
        s.add("sdfjksdklfj");
        s.add("sdf");
        s.add("sdfdsf");
        s.add("dsf");
        s.add("dsfsdf");
        s.add("sdfjksdklfj");
        s.add("sdf");
        s.add("sdfdsf");

//        ArrayList<JSong> thisi = new ArrayList<>();
//        thisi.add(new JSong("My albums"," fd", "./assets/sampleAlbum.png"));
//        thisi.add(new JSong("My albums"," fd1", "./assets/sampleAlbum.png"));
//        thisi.add(new JSong("My albums"," fd21", "./assets/sampleAlbum.png"));
//        thisi.add(new JSong("My albums"," fd5", "./assets/sampleAlbum.png"));
//        thisi.add(new JSong("My albums"," fd5", "./assets/sampleAlbum.png"));
//        thisi.add(new JSong("My albums"," fd4", "./assets/sampleAlbum.png"));
//        thisi.add(new JSong("My albums"," fd6", "./assets/sampleAlbum.png"));

//        CreateNewPlaylist p = new CreateNewPlaylist(s);
//        PlaylistList pl = new PlaylistList("play", s);
//        pl.setVisible(true);

//        p.setVisible(true);

    }

}
