package org.example.server;

import java.util.ArrayList;
import java.util.List;

public class UserDatabase {
    private List<User> users;

    public UserDatabase() {
        users = new ArrayList<>();

        users.add(new User("Roman", "pass1"));
        users.add(new User("Ivan", "pass2"));
        users.add(new User("Alex", "pass3"));
    }

    public boolean authenticateUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) &&
                    user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }
}
