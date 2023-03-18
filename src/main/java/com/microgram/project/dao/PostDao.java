package com.microgram.project.dao;

import com.microgram.project.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PostDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Post> getAllPosts() {
        String sql = "select * from posts";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Post.class));
    }

    public List<Post> getPostsOfUser(Long userId) {
        String sql = String.format("select * from posts where user_id = %s", userId);
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Post.class));
    }

    public List<Post> getPostsOfFollowedUsers(Long userId) {
        String sql = String.format("select p.id, p.image, p.description, p.date, p.user_id from posts as p " +
                "full join users as u on p.user_id = u.id " +
                "full join subscriptions as s on u.id = s.subscribed_to_id " +
                "where subscriber_id = %s;", userId);
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Post.class));
    }
}
