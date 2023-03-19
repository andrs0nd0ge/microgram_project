package com.microgram.project.controller;

import com.microgram.project.dto.PostDto;
import com.microgram.project.service.PostService;
import com.microgram.project.util.FileServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final FileServiceImpl fileService;
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
    @PostMapping("/post/{description}/{userId}")
    public void makePost(@RequestParam("file") MultipartFile file, @PathVariable String description, @PathVariable Long userId) {
        postService.makePost(file, description, userId);
        fileService.save(file);
    }
    @DeleteMapping("/post/{userId}/{postId}")
    public void deletePost(@PathVariable Long userId, @PathVariable Long postId) {
        postService.deletePost(userId, postId);
    }
    @GetMapping("/image/{postId}")
    public ResponseEntity<Resource> getPictureOfPost(@PathVariable Long postId) {
        Resource resource = postService.getPictureOfPost(postId);
        if (resource != null) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_PNG_VALUE)
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
