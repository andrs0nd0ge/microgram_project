package com.microgram.project.dao;

import com.microgram.project.entity.Post;
import com.microgram.project.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
public class UserDao {
    private final JdbcTemplate jdbcTemplate;
    private final Connection connection;
    public List<User> getUsersByUsername(String username) throws SQLException {
        String sql = "select * from public.users where username = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> getUsersByName(String name) throws SQLException {
        String sql = "select * from public.users where name = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> getUserByEmail(String email) throws SQLException {
        String sql = "select * from public.users where email = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public boolean checkIfUserExists(String email) throws SQLException {
        String sql = "select * from public.users where email = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        User user = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class)).stream()
                .findFirst()
                .orElse(null);
        return user != null;
    }

    public List<Post> getAllPosts() {
        String sql = "select * from public.posts";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Post.class));
    }

    public List<Post> getPostOfUser(Long userId) throws SQLException {
        String sql = "select * from public.posts where user_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, userId);
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Post.class));
    }
}
