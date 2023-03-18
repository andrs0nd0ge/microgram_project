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
}
