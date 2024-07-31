package com.microgram.project.dao;

import com.microgram.project.entity.Like;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LikeDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    public List<Like> getAllLikes() {
        String sql = "select * from likes";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Like.class));
    }
    public Like checkPostForLikes(Long postId) {
        String sql = "select * from likes as l " +
                "left join posts as p on p.id = l.post_id " +
                "where post_id = :postId";
        return namedJdbcTemplate.query(sql,
                        new MapSqlParameterSource().addValue("postId", postId),
                        new BeanPropertyRowMapper<>(Like.class))
                .stream()
                .findAny()
                .orElse(null);
    }
}
