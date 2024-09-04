package com.microgram.project.service;

import com.microgram.project.dao.PostDao;
import com.microgram.project.dto.CommentForPostsDto;
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

        long userId = 0L;
        if (user != null) {
            userId = user.getId();
        }

        List<Post> posts = postDao.getPostsOfFollowedUsers(userId);
        return posts.stream()
                .map(PostDto::from)
                .collect(Collectors.toList());
    }

    public List<PostDto> getPostsOfOtherUsers(Authentication auth) {
        User user = (User) auth.getPrincipal();

        long id = 0L;
        if (user != null) {
            id = user.getId();
        }

        List<Post> posts = postDao.getPostsOfOtherUsers(id);
        return posts.stream()
                .map(PostDto::from)
                .collect(Collectors.toList());
    }

    public void leaveCommentOnPost(CommentForPostsDto commentDto) {
        postDao.leaveCommentOnPost(commentDto);
    }

    public void leaveLikeUnderPost(Authentication auth, Long postId) {
        User user = (User) auth.getPrincipal();

        long userId = 0L;
        if (user != null) {
            userId = user.getId();
        }

        postDao.leaveLikeUnderPost(userId, postId);
    }

    public void deleteCommentOnPost(CommentForPostsDto commentDto) {
        postDao.deleteCommentOnPost(commentDto);
    }

    public void makePost(MultipartFile file, String description, Authentication auth) {
        try {
            User user = (User) auth.getPrincipal();

            long userId = 0L;
            if (user != null) {
                userId = user.getId();
            }

            postDao.makePost(file, description, userId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deletePost(Authentication auth, Long postId) {
        User user = (User) auth.getPrincipal();

        long userId = 0L;
        if (user != null) {
            userId = user.getId();
        }

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

    public void unlikePost(Authentication auth, Long postId) {
        User user = (User) auth.getPrincipal();

        long userId = 0L;
        if (user != null) {
            userId = user.getId();
        }

        postDao.unlikePost(userId, postId);
    }
}
