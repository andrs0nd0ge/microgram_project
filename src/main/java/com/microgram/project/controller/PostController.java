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
import org.springframework.security.core.Authentication;
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
    public ResponseEntity<List<PostDto>> getPostsOfOtherUsers(Authentication auth) {
        List<PostDto> posts = postService.getPostsOfOtherUsers(auth);
        if (posts.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/feed")
    public ResponseEntity<List<PostDto>> getPostsOfFollowedUsers(Authentication auth) {
        List<PostDto> posts = postService.getPostsOfFollowedUsers(auth);
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
    public void deleteCommentOnPost(@RequestBody CommentForPostsDto commentDto) {
        postService.deleteCommentOnPost(commentDto);
    }

    @GetMapping("/like/{postId}")
    public void leaveLikeUnderPost(Authentication auth, @PathVariable Long postId) {
        postService.leaveLikeUnderPost(auth, postId);
    }

    @DeleteMapping("/unlike/{postId}")
    public void unlikePost(Authentication auth, @PathVariable Long postId) {
        postService.unlikePost(auth, postId);
    }

    @PostMapping("/make-post")
    public void makePost(@RequestParam("imageFile") MultipartFile file,
                         @RequestParam("desc") String description,
                         Authentication auth) {
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
