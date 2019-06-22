package jpotify.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Users implements Serializable {
    private static final long serialVersionUID = -4366011389578880105L;
    private ArrayList<User> users = new ArrayList<>();

    public void addUser(User user) {
        if (user != null)
            users.add(user);
    }

    public User getUser(int index) {
        return users.get(index);
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
