package jpotify.controller;

import jpotify.model.Song;
import jpotify.model.User;
import jpotify.view.MainView;

import java.io.File;
import java.io.FileNotFoundException;

public class MainController {
    private MainView mainView;
    private User user;

    public MainController() {
        mainView = new MainView(this);
        user = new User("Radin");
    }

    public void addSongToLibrary(File... files) {
        for (File f :
                files) {
            try {
                user.addSong(new Song(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
