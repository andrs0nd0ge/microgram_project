package com.microgram.project.dao;

import com.microgram.project.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    public List<User> getAllUsers() {
        String sql = "select * from microgram.users";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }
    public Optional<User> getUserByUsername(String username) {
        String sql = "select * from microgram.users where lower(username) like concat('%', lower(:username), '%')";
        return namedJdbcTemplate.query(sql,
                        new MapSqlParameterSource().addValue("username", username),
                        new BeanPropertyRowMapper<>(User.class))
                .stream()
                .findFirst();
    }
    public List<User> getUsersByName(String name) {
        String sql = "select * from microgram.users where lower(name) like concat('%', lower(:name), '%')";
        return namedJdbcTemplate.query(sql,
                new MapSqlParameterSource().addValue("name", name),
                new BeanPropertyRowMapper<>(User.class));
    }

    public Optional<User> getUserByEmail(String email) {
        String sql = "select * from microgram.users where lower(email) like lower('%', lower(:email) '%')";
        return namedJdbcTemplate.query(sql,
                        new MapSqlParameterSource().addValue("email", email),
                        new BeanPropertyRowMapper<>(User.class))
                .stream()
                .findFirst();
    }

    public Optional<User> checkIfUserExistsByEmail(String email) {
        String sql = "select * from microgram.users where lower(email) like lower('%', lower(:email)'%')";
        return namedJdbcTemplate.query(sql,
                        new MapSqlParameterSource().addValue("email", email),
                        new BeanPropertyRowMapper<>(User.class))
                .stream()
                .findFirst();
    }

    public Optional<User> checkIfUserExistsByUsername(String username) {
        String sql = "select * from microgram.users where lower(username) like lower('%', lower(:username)'%')";
        return namedJdbcTemplate.query(sql,
                        new MapSqlParameterSource().addValue("username", username),
                        new BeanPropertyRowMapper<>(User.class))
                .stream()
                .findFirst();
    }

    public void registerUser(String name, String username, String email, String password) {
        String sql = "insert into microgram.users (name, username, email, password) " +
                "values (:name, :username, :email, :password)";
        namedJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("name", name)
                .addValue("username", username)
                .addValue("email", email)
                .addValue("password", password));
    }
}
