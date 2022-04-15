package com.mdtech.restfulwebservices.controllers;

import com.mdtech.restfulwebservices.entity.Post;
import com.mdtech.restfulwebservices.entity.User;
import com.mdtech.restfulwebservices.services.PostService;
import com.mdtech.restfulwebservices.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class PostController {

    @Autowired
    private UserServices userServices;

    @Autowired
    private PostService postService;

    @GetMapping("/jpa/user/{userId}/posts")
    public List<Post> getAllPost(@PathVariable Long userId) {
        return userServices.getPosts(userId);
    }

    @PostMapping("/jpa/user/{userId}/post")
    public ResponseEntity<Object> createPost(@PathVariable Long userId, @RequestBody Post post) {
        User user = userServices.getUserById(userId);
        post.setUser(user);
        Post post1 = postService.createPost(post);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(post1.getId())
                .toUri();

        return ResponseEntity.created(uri).build();

    }

}
