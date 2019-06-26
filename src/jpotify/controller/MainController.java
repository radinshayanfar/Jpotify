package jpotify.controller;

import jpotify.model.User;
import jpotify.view.MainView;

import java.io.File;

public class MainController {
    private MainView mainView;
    private User user;

    public MainController() {
        mainView = new MainView(this);
        user = new User("Radin");
    }

    public void addSongToLibrary(File f){
        
    }
}
