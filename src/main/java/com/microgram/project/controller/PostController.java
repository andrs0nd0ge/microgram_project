package com.microgram.project.controller;

import com.microgram.project.dto.PostDto;
import com.microgram.project.service.PostService;
import com.microgram.project.util.FileServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<PostDto>> getPostsOfUser(@PathVariable Long userId) {
        List<PostDto> posts = postService.getPostsOfUser(userId);
        if (posts.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<PostDto>> getPostsOfOtherUsers(Long userId) {
        List<PostDto> posts = postService.getPostsOfOtherUsers(userId);
        if (posts.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
    @GetMapping("/feed")
    public ResponseEntity<List<PostDto>> getPostsOfFollowedUsers(Long userId) {
        List<PostDto> posts = postService.getPostsOfFollowedUsers(userId);
        if (posts.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
    @PostMapping("/comment/{postId}/{comment}")
    public void leaveCommentOnPost(@PathVariable Long postId, @PathVariable String comment) {
        postService.leaveCommentOnPost(postId, comment);
    }
    @DeleteMapping("/comment/{postId}/{commentId}")
    public void deleteCommentOnPost(@RequestParam("id") Long userId, @PathVariable Long postId, @PathVariable Long commentId) {
        postService.deleteCommentOnPost(userId, postId, commentId);
    }
    @GetMapping("/like/{postId}")
    public void leaveLikeUnderPost(@RequestParam("id") Long userId, @PathVariable Long postId) {
        postService.leaveLikeUnderPost(userId, postId);
    }
    @DeleteMapping("/unlike/{postId}")
    public void unlikePost(@RequestParam("id") Long userId, @PathVariable Long postId) {
        postService.unlikePost(userId, postId);
    }
    @PostMapping("/post/{description}")
    public void makePost(@RequestParam("file") MultipartFile file,
                         @PathVariable String description,
                         @RequestParam("id") Long userId) {
        postService.makePost(file, description, userId);
        fileService.save(file);
    }
    @DeleteMapping("/post/{postId}")
    public void deletePost(@RequestParam("id") Long userId, @PathVariable Long postId) {
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
