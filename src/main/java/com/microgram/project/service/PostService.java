package com.microgram.project.service;

import com.microgram.project.dao.PostDao;
import com.microgram.project.dto.PostDto;
import com.microgram.project.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostDao postDao;

    public List<PostDto> getAllPosts() {
        List<Post> posts = postDao.getAllPosts();
        return posts.stream()
                .map(PostDto::from)
                .collect(Collectors.toList());
    }

    public List<PostDto> getPostsOfUser(Long userId) {
        List<Post> posts = postDao.getPostsOfUser(userId);
        return posts.stream()
                .map(PostDto::from)
                .collect(Collectors.toList());
    }

    public List<PostDto> getPostsOfFollowedUser(Long userId) {
        List<Post> posts = postDao.getPostsOfFollowedUsers(userId);
        return posts.stream()
                .map(PostDto::from)
                .collect(Collectors.toList());
    }

    public void leaveCommentOnPost(Long postId, String comment) {
        postDao.leaveCommentOnPost(postId, comment);
    }

    public void leaveLikeUnderPost(Long userId, Long postId) {
        postDao.leaveLikeUnderPost(userId, postId);
        System.out.println("Like was added successfully");
    }

    public void deleteCommentOnPost(Long userId, Long postId, Long commentId) {
        postDao.deleteCommentOnPost(userId, postId, commentId);
    }

    public List<PostDto> getPostsOfOtherUsers(Long userId) {
        List<Post> posts = postDao.getPostsOfOtherUsers(userId);
        return posts.stream()
                .map(PostDto::from)
                .collect(Collectors.toList());
    }
}
