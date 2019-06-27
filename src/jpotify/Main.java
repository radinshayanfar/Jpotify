package jpotify;

import helper.FileHelper;
import jpotify.controller.MainController;
import jpotify.model.User;
import jpotify.model.Users;

import javax.swing.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        Users users;
        try {
            users = FileHelper.loadUsers();
        } catch (IOException | ClassNotFoundException e) {
            users = new Users();
            users.addUser(new User("Radin"));
        }
        new MainController(users, 0);

    }

}
