package com.microgram.project.dao;

import com.microgram.project.entity.Like;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@RequiredArgsConstructor
public class LikeDao {
    private final JdbcTemplate jdbcTemplate;
    private final Connection connection;
    public Boolean checkPostForLikes(Long postId) throws SQLException {
        String sql = "select * from public.likes l\n" +
                "left join public.posts p on p.id = l.post_id\n" +
                "where post_id = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, postId);
        Like like = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Like.class))
                .stream()
                .findAny()
                .orElse(null);
        return like != null;
    }
}
