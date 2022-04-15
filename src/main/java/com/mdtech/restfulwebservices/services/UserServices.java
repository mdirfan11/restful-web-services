package com.mdtech.restfulwebservices.services;

import com.mdtech.restfulwebservices.entity.Post;
import com.mdtech.restfulwebservices.entity.User;
import com.mdtech.restfulwebservices.exceptions.UserNotFoundException;
import com.mdtech.restfulwebservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User not found with user id : "+id);
        }
        return user.get();
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        User old  = getUserById(user.getId());
        old.setEmail(user.getEmail());
        old.setUsername(user.getUsername());
        return userRepository.save(old);
    }

    public void deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.deleteById(id);
    }

    public List<Post> getPosts(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User not found with user id : "+userId);
        }
        return user.get().getPosts();
    }
}
