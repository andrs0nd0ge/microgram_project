package com.microgram.project.controller;

import com.microgram.project.dto.PostDto;
import com.microgram.project.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @GetMapping("/main")
    public List<PostDto> getPosts() {
        return postService.getAllPosts();
    }
    @GetMapping("/{userId}")
    public List<PostDto> getPostsOfUser(@PathVariable Long userId) {
        return postService.getPostsOfUser(userId);
    }
    @GetMapping
    public List<PostDto> getPostsOfOtherUsers(@RequestParam Long userId) {
        return postService.getPostsOfOtherUsers(userId);
    }
    @GetMapping("/feed/{userId}")
    public List<PostDto> getPostsOfFollowedUser(@PathVariable Long userId) {
        return postService.getPostsOfFollowedUser(userId);
    }
    @PostMapping("/comment/{postId}/{comment}")
    public void leaveCommentOnPost(@PathVariable Long postId, @PathVariable String comment) {
        postService.leaveCommentOnPost(postId, comment);
    }
    @DeleteMapping("/comment/{userId}/{postId}/{commentId}")
    public void deleteCommentOnPost(@PathVariable Long userId, @PathVariable Long postId, @PathVariable Long commentId) {
        postService.deleteCommentOnPost(userId, postId, commentId);
    }
    @GetMapping("/like/{userId}/{postId}")
    public void leaveLikeUnderPost(@PathVariable Long userId, @PathVariable Long postId) {
        postService.leaveLikeUnderPost(userId, postId);
    }
}
