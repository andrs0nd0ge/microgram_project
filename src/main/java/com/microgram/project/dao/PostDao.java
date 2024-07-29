package com.microgram.project.dao;

import com.microgram.project.dto.CommentForPostsDto;
import com.microgram.project.entity.Post;
import com.microgram.project.mappers.PostUserRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PostDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    public List<Post> getAllPosts() {
        String sql = "select p.id as post_id, p.image_name, p.description, p.date, " +
                "u.id as user_id, u.name, u.username, u.email " +
                "from posts p " +
                "inner join users u on p.user_id = u.id";
        return jdbcTemplate.query(sql, new PostUserRowMapper());
    }

    public List<Post> getPostsOfUser(Long userId) {
        String sql = "select p.id as post_id, p.user_id, p.image_name, p.description, p.date, " +
                "u.id as user_id, u.name, u.username, u.email " +
                "from posts p " +
                "inner join users u on p.user_id = u.id " +
                "where p.user_id = :userId";
        return namedJdbcTemplate.query(sql, new MapSqlParameterSource()
                        .addValue("userId", userId),
                new PostUserRowMapper());
    }

    public List<Post> getPostsOfFollowedUsers(Long userId) {
        String sql = "select p.id as post_id, p.user_id, p.image_name, p.description, p.date, " +
                "u.id as user_id, u.name, u.username, u.email " +
                "from posts as p " +
                "left join users as u on p.user_id = u.id " +
                "left join subscriptions as s on u.id = s.subscribed_to_id " +
                "where subscriber_id = :subscriberId";
        return namedJdbcTemplate.query(sql, new MapSqlParameterSource()
                        .addValue("subscriberId", userId),
                new PostUserRowMapper());
    }

    public void updatePostsQty(Long userId) {
        String sql = "update users set post_qty = (select count(user_id) from users as u " +
                "left join posts p on u.id = p.user_id " +
                "where u.id = :userId " +
                "group by u.id) " +
                "where id = :userId";
        jdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("userId", userId));
    }

    public List<Post> getPostsOfOtherUsers(Long userId) {
        String sql = "select p.id as post_id, p.user_id, p.image_name, p.description, p.date, " +
                "u.id as user_id, u.name, u.username, u.email " +
                "from posts p " +
                "inner join users u on p.user_id = u.id " +
                "where p.user_id != :userId";
        return namedJdbcTemplate.query(sql, new MapSqlParameterSource()
                        .addValue("userId", userId),
                new PostUserRowMapper());
    }

    public void leaveCommentOnPost(CommentForPostsDto commentDto) {
        long postId = commentDto.getPostId();
        long userId = commentDto.getUserId();
        String comment = commentDto.getComment();

        String sql = "insert into comments (text, date, post_id, user_id) " +
                "values (:comment, current_timestamp, :postId, :userId)";

        namedJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("comment", comment)
                .addValue("postId", postId)
                .addValue("userId", userId));
    }

    public void deleteCommentOnPost(CommentForPostsDto commentDto) {
        long postId = commentDto.getPostId();
        long userId = commentDto.getUserId();
        long commentId = commentDto.getCommentId();

        String sql = "delete from comments as c " +
                "where c.id = :commentId and c.post_id = :postId and c.user_id = :userId";

        namedJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("commentId", commentId)
                .addValue("postId", postId)
                .addValue("userId", userId));
    }

    public void leaveLikeUnderPost(Long userId, Long postId) {
        String sql = "insert into likes (user_id, post_id, date) " +
                "values (:userId, :postId, current_timestamp)";

        namedJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("userId", userId)
                .addValue("postId", postId));
    }

    public void makePost(MultipartFile file, String description, Long userId) throws IOException {
        String filename = file.getOriginalFilename();
        byte[] image = file.getBytes();

        String sql = "insert into posts (image, image_name, description, date, user_id) " +
                "values (:image, :filename, :description, current_timestamp, :userId)";

        namedJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("image", image)
                .addValue("filename", filename)
                .addValue("description", description)
                .addValue("userId", userId)
        );

        updatePostsQty(userId);
    }

    public void deletePost(Long userId, Long postId) {
        String sql = "delete from posts " +
                "where user_id = :userId and id = :postId";

        namedJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("userId", userId)
                .addValue("postId", postId));

        updatePostsQty(userId);
    }

    public Post getPostWithPicture(Long postId) {
        String sql = "select image from posts where id = :postId";
        return namedJdbcTemplate.query(
                        sql,
                        new MapSqlParameterSource().addValue("postId", postId),
                        new BeanPropertyRowMapper<>(Post.class)
                )
                .stream()
                .findFirst()
                .orElse(null);
    }

    public void unlikePost(Long userId, Long postId) {
        String sql = "delete from likes " +
                "where user_id = :userId and post_id = :postId";
        namedJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("userId", userId)
                .addValue("postId", postId));
    }
}
