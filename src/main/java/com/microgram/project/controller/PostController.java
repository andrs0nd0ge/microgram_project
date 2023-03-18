package com.microgram.project.controller;

import com.microgram.project.dto.PostDto;
import com.microgram.project.service.PostService;
import com.microgram.project.util.UtilityClass;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final UtilityClass util;
    @GetMapping
    public List<PostDto> getPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{userId}")
    public List<PostDto> getPostsOfUser(@PathVariable Long userId) {
        return postService.getPostsOfUser(userId);
    }

    @GetMapping("/feed/{userId}")
    public List<PostDto> getPostsOfFollowedUser(@PathVariable Long userId) {
        return postService.getPostsOfFollowedUser(userId);
    }

    @GetMapping("/insert")
    public ResponseEntity<String> insertIntoPostsTable() {
        return new ResponseEntity<>(util.insertIntoPosts(), HttpStatus.OK);
    }

    @GetMapping("/create")
    public ResponseEntity<String> createPostsTable() {
        return new ResponseEntity<>(util.createPostsTable(), HttpStatus.OK);
    }
}
