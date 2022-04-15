package com.mdtech.restfulwebservices.user;

import com.mdtech.restfulwebservices.entity.Post;
import com.mdtech.restfulwebservices.exceptions.UserNotFoundException;
import com.mdtech.restfulwebservices.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mdtech.restfulwebservices.entity.User;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJPAController {

    @Autowired
    private UserServices userServices;

    @GetMapping("/jpa/users")
    public List<User> getUsers() {
        return userServices.getAllUsers();
    }

    @GetMapping("/jpa/user/{userId}")
    public EntityModel<User> getUserById(@PathVariable Long userId) {
        User user = userServices.getUserById(userId);

        EntityModel<User> model = EntityModel.of(user);
        WebMvcLinkBuilder linkToUsers = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).getUsers());
        model.add(linkToUsers.withRel("all-users"));
        return model;
    }

    @PostMapping("/jpa/user")
    public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
        System.out.println(user.toString());
        User savedUser = userServices.addUser(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/jpa/user")
    public User updateUser(@RequestBody User user) {
        return userServices.updateUser(user);
    }

    @DeleteMapping("/jpa/user/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userServices.deleteUser(userId);
    }

}
