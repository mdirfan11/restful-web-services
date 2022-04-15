package com.mdtech.restfulwebservices.services;

import com.mdtech.restfulwebservices.entity.Post;
import com.mdtech.restfulwebservices.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

}
