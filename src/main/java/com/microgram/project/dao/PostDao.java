package com.microgram.project.dao;

import com.microgram.project.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
public class PostDao {
    private final JdbcTemplate jdbcTemplate;
    private final Connection connection;

    public List<Post> getAllPosts() {
        String sql = "select * from public.posts";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Post.class));
    }

    public List<Post> getPostsOfUser(Long userId) throws SQLException {
        String sql = "select * from public.posts where user_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, userId);
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Post.class));
    }

    public List<Post> getPostsOfFollowedUsers(Long userId) throws SQLException {
        String sql = "select p.id, image, description, p.date from public.posts as p\n" +
                "full join public.users as u on p.user_id = u.id\n" +
                "full join public.subscriptions as s on u.id = s.subscribed_to_id\n" +
                "where subscriber_id = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, userId);
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Post.class));
    }
}
