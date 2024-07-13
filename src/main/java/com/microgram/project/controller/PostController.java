package com.microgram.project.controller;

import com.microgram.project.dto.CommentForPostsDto;
import com.microgram.project.dto.PostDto;
import com.microgram.project.service.PostService;
import com.microgram.project.util.FileService;
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
    private final FileService fileService;
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

    @PostMapping("/comment")
    public void leaveCommentOnPost(@RequestBody CommentForPostsDto commentDto) {
        postService.leaveCommentOnPost(commentDto);
    }

    @DeleteMapping("/comment")
    public void deleteCommentOnPost(
//            @RequestParam("id") Long userId,
//            @PathVariable Long postId,
//            @PathVariable Long commentId
            @RequestBody CommentForPostsDto commentDto
    ) {
        postService.deleteCommentOnPost(commentDto);
    }

    @GetMapping("/like/{postId}")
    public void leaveLikeUnderPost(@RequestParam("id") Long userId, @PathVariable Long postId) {
        postService.leaveLikeUnderPost(userId, postId);
    }

    @DeleteMapping("/unlike/{postId}")
    public void unlikePost(@RequestParam("id") Long userId, @PathVariable Long postId) {
        postService.unlikePost(userId, postId);
    }

    @PostMapping("/make-post")
    public void makePost(@RequestParam("imageFile") MultipartFile file,
                         @RequestParam("desc") String description,
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
