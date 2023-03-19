package com.microgram.project.dao;

import com.microgram.project.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDao {
    private final JdbcTemplate jdbcTemplate;
    public List<User> getAllUsers() {
        String sql = "select * from users";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }
    public Optional<User> getUserByUsername(String username) {
        String sql = String.format("select * from users where username like '%%%s%%'", username);
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class))
                .stream()
                .findFirst();
    }

    public List<User> getUsersByName(String name) {
        String sql = String.format("select * from users where name like '%%%s%%'", name);
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public Optional<User> getUserByEmail(String email) {
        String sql = String.format("select * from users where email like '%%%s%%'", email);
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class))
                .stream()
                .findFirst();
    }

    public Optional<User> checkIfUserExistsByEmail(String email) {
        String sql = String.format("select * from users where email like '%%%s%%'", email);
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class))
                .stream()
                .findFirst();
    }

    public Optional<User> checkIfUserExistsByUsername(String username) {
        String sql = String.format("select * from users where username like '%%%s%%'", username);
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class))
                .stream()
                .findFirst();
    }

    public void registerUser(String name, String username, String email, String password) {
        String sql = String.format("insert into users (name, username, email, password) " +
                "values ('%s', '%s', '%s', '%s')", name, username, email, password);
        jdbcTemplate.update(sql);
    }
}
