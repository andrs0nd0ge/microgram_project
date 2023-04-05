package com.microgram.project.service;

import com.microgram.project.dao.PostDao;
import com.microgram.project.dto.PostDto;
import com.microgram.project.entity.Post;
import com.microgram.project.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.security.core.Authentication;
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

    public List<PostDto> getPostsOfFollowedUsers(Authentication auth) {
        User user = (User) auth.getPrincipal();
        List<Post> posts = postDao.getPostsOfFollowedUsers(user.getId());
        return posts.stream()
                .map(PostDto::from)
                .collect(Collectors.toList());
    }

    public List<PostDto> getPostsOfOtherUsers(Authentication auth) {
        User user = (User) auth.getPrincipal();
        List<Post> posts = postDao.getPostsOfOtherUsers(user.getId());
        return posts.stream()
                .map(PostDto::from)
                .collect(Collectors.toList());
    }

    public void leaveCommentOnPost(Long postId, String comment) {
        postDao.leaveCommentOnPost(postId, comment);
    }

    public void leaveLikeUnderPost(Authentication auth, Long postId) {
        User user = (User) auth.getPrincipal();
        postDao.leaveLikeUnderPost(user.getId(), postId);
    }

    public void deleteCommentOnPost(Authentication auth, Long postId, Long commentId) {
        User user = (User) auth.getPrincipal();
        postDao.deleteCommentOnPost(user.getId(), postId, commentId);
    }

    public void makePost(MultipartFile file, String description, Authentication auth) {
        User user = (User) auth.getPrincipal();
        try {
            postDao.makePost(file, description, user.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deletePost(Authentication auth, Long postId) {
        User user = (User) auth.getPrincipal();
        postDao.deletePost(user.getId(), postId);
    }

    public Resource getPictureOfPost(Long postId) {
        Post post = postDao.getPostWithPicture(postId);
        if (post != null) {
            return new ByteArrayResource(post.getImage());
        } else {
            return null;
        }
    }

    public void unlikePost(Authentication auth, Long postId) {
        User user = (User) auth.getPrincipal();
        postDao.unlikePost(user.getId(), postId);
    }
}
