package com.mdtech.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDAOService userDAOService;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userDAOService.findAll();
    }

    @GetMapping("/user/{userId}")
    public User getUserById(@PathVariable int userId) {
        return userDAOService.findById(userId);
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user) {
        return userDAOService.addUser(user);
    }

    @PutMapping("/user")
    public User updateUser(@RequestBody User user) {
        return userDAOService.updateUser(user);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestBody User user) {
        userDAOService.deleteUser(user);
    }

}
