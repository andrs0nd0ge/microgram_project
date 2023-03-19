package com.microgram.project.dao;

import com.microgram.project.entity.Like;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LikeDao {
    private final JdbcTemplate jdbcTemplate;
    public List<Like> getAllLikes() {
        String sql = "select * from likes";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Like.class));
    }
    public Like checkPostForLikes(Long postId) {
        String sql = String.format("select * from likes as l " +
                "left join posts as p on p.id = l.post_id " +
                "where post_id = %s;", postId);
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Like.class))
                .stream()
                .findAny()
                .orElse(null);
    }
}
