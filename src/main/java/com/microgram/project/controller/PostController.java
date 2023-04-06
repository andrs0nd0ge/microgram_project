package com.microgram.project.controller;

import com.microgram.project.dto.PostDto;
import com.microgram.project.service.PostService;
import com.microgram.project.util.FileServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    public List<PostDto> getPostsOfOtherUsers(Authentication auth) {
        return postService.getPostsOfOtherUsers(auth);
    }
    @GetMapping("/feed")
    public List<PostDto> getPostsOfFollowedUsers(Authentication auth) {
        return postService.getPostsOfFollowedUsers(auth);
    }
    @PostMapping("/comment/{postId}/{comment}")
    public void leaveCommentOnPost(@PathVariable Long postId, @PathVariable String comment) {
        postService.leaveCommentOnPost(postId, comment);
    }
    @DeleteMapping("/comment/{postId}/{commentId}")
    public void deleteCommentOnPost(Authentication auth, @PathVariable Long postId, @PathVariable Long commentId) {
        postService.deleteCommentOnPost(auth, postId, commentId);
    }
    @GetMapping("/like/{postId}")
    public void leaveLikeUnderPost(Authentication auth, @PathVariable Long postId) {
        postService.leaveLikeUnderPost(auth, postId);
    }
    @DeleteMapping("/unlike/{postId}")
    public void unlikePost(Authentication auth, @PathVariable Long postId) {
        postService.unlikePost(auth, postId);
    }
    @PostMapping("/post/{description}")
    public void makePost(@RequestParam("file") MultipartFile file, @PathVariable String description, Authentication auth) {
        postService.makePost(file, description, auth);
        fileService.save(file);
    }
    @DeleteMapping("/post/{postId}")
    public void deletePost(Authentication auth, @PathVariable Long postId) {
        postService.deletePost(auth, postId);
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
