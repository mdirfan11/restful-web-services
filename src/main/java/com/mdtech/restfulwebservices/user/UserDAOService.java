package com.mdtech.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserDAOService {

    private static List<User> users = new ArrayList<>();
    private static int userCount = 5;
    static {
        users.add(new User(1, "MD Irfan", "irfan@gmail.com"));
        users.add(new User(2, "MD Rizwan", "rizwan@gmail.com"));
        users.add(new User(3, "MD Arman", "arman@gmail.com"));
        users.add(new User(4, "MD Imran", "imran@gmail.com"));
        users.add(new User(5, "MD Gufran", "gufran@gmail.com"));
    }

    public List<User> findAll() {
        return users;
    }

    public User findById(int userId) {
        Optional<User> user = users.stream().filter(u -> u.getUserId() == userId).findFirst();
        if (user.isEmpty()) {
            return null;
        }
        return user.get();
    }

    public User addUser(User user) {
        user.setUserId(++userCount);
        users.add(user);
        return user;
    }

    public User updateUser(User user) {
        User u = findById(user.getUserId());
        u.setEmail(user.getEmail());
        u.setName(user.getName());
        return u;
    }

    public boolean deleteUser(int userId) {
        return users.removeIf(u -> u.getUserId() == userId);
    }
}
