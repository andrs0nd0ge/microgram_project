package com.microgram.project.service;

import com.microgram.project.dao.PostDao;
import com.microgram.project.dto.PostDto;
import com.microgram.project.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    public List<PostDto> getPostsOfFollowedUsers(Long id) {
        List<Post> posts = postDao.getPostsOfFollowedUsers(id);
        return posts.stream()
                .map(PostDto::from)
                .collect(Collectors.toList());
    }

    public List<PostDto> getPostsOfOtherUsers(Long id) {
        List<Post> posts = postDao.getPostsOfOtherUsers(id);
        return posts.stream()
                .map(PostDto::from)
                .collect(Collectors.toList());
    }

    public void leaveCommentOnPost(Long postId, String comment) {
        postDao.leaveCommentOnPost(postId, comment);
    }

    public void leaveLikeUnderPost(Long userId, Long postId) {
        postDao.leaveLikeUnderPost(userId, postId);
    }

    public void deleteCommentOnPost(Long userId, Long postId, Long commentId) {
        postDao.deleteCommentOnPost(userId, postId, commentId);
    }

    public void makePost(MultipartFile file, String description, Long userId) {
        try {
            postDao.makePost(file, description, userId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deletePost(Long userId, Long postId) {
        postDao.deletePost(userId, postId);
    }

    public Resource getPictureOfPost(Long postId) {
        Post post = postDao.getPostWithPicture(postId);
        if (post != null) {
            return new ByteArrayResource(post.getImage());
        } else {
            return null;
        }
    }

    public void unlikePost(Long userId, Long postId) {
        postDao.unlikePost(userId, postId);
    }
}
