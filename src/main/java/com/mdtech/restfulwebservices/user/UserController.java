package com.mdtech.restfulwebservices.user;

import com.mdtech.restfulwebservices.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {

    @Autowired
    private UserDAOService userDAOService;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userDAOService.findAll();
    }

    @GetMapping("/user/{userId}")
    public EntityModel<User> getUserById(@PathVariable int userId) {
        User user = userDAOService.findById(userId);

        if (user == null)
            throw new UserNotFoundException("UserId : "+userId);

        EntityModel<User> model = EntityModel.of(user);
        WebMvcLinkBuilder linkToUsers = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).getUsers());
        model.add(linkToUsers.withRel("all-users"));
        return model;
    }

    @PostMapping("/user")
    public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
        User savedUser = userDAOService.addUser(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(savedUser.getUserId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/user")
    public User updateUser(@RequestBody User user) {
        return userDAOService.updateUser(user);
    }

    @DeleteMapping("/user/{userId}")
    public void deleteUser(@PathVariable int userId) {
        boolean isUserDeleted = userDAOService.deleteUser(userId);
        if (!isUserDeleted)
            throw new UserNotFoundException("User with userId : "+userId+" NOT FOUND");
    }

}
