package com.microgram.project.dao;

import com.microgram.project.entity.Like;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LikeDao {
    private final JdbcTemplate jdbcTemplate;
    public List<Like> getAllLikes() {
        String sql = "select * from likes";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Like.class));
    }
    public Optional<Like> checkPostForLikes(Long postId) {
        String sql = String.format("select * from likes l " +
                "left join posts p on p.id = l.post_id " +
                "where post_id = %s;", postId);
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Like.class))
                .stream()
                .findAny();
    }
}
