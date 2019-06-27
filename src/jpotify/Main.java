package jpotify;

import helper.FileHelper;
import jpotify.controller.MainController;
import jpotify.model.User;
import jpotify.model.Users;

import java.io.IOException;

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

    }

}
