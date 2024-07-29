package com.microgram.project.dao;

import com.microgram.project.entity.Comment;
import com.microgram.project.mappers.CommentPostUserRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    public List<Comment> getAllComments() {
        String sql = "select c.id as comment_id, c.text, c.date as comment_date, " +
                "p.id as post_id, p.image_name, p.description, p.date as post_date, " +
                "u.id as user_id, u.name, u.username, u.email " +
                "from comments as c " +
                "inner join posts as p on c.post_id = p.id " +
                "inner join users as u on c.user_id = u.id";
        return jdbcTemplate.query(sql, new CommentPostUserRowMapper());
    }

    public List<Comment> getCommentsForPost(long postId) {
        String sql = "select c.id as comment_id, c.text, c.date as comment_date, " +
                "p.id as post_id, p.image_name, p.description, p.date as post_date, " +
                "u.id as user_id, u.name, u.username, u.email " +
                "from comments as c " +
                "inner join posts as p on c.post_id = p.id " +
                "inner join users as u on c.user_id = u.id " +
                "where c.post_id = :postId";

        return namedJdbcTemplate.query(sql, new MapSqlParameterSource()
                        .addValue("postId", postId),
                new CommentPostUserRowMapper());
    }
}
