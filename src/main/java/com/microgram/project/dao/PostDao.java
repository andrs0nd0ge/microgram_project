package com.microgram.project.dao;

import com.microgram.project.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PostDao {
    private final JdbcTemplate jdbcTemplate;
    public List<Post> getAllPosts() {
        String sql = "select * from posts";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Post.class));
    }

    public List<Post> getPostsOfUser(Long userId) {
        String sql = String.format("select * from posts where user_id = %s;", userId);
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Post.class));
    }

    public List<Post> getPostsOfFollowedUsers(Long userId) {
        String sql = String.format("select p.id, p.image, p.description, p.date, p.user_id from posts as p " +
                "full join users as u on p.user_id = u.id " +
                "full join subscriptions as s on u.id = s.subscribed_to_id " +
                "where subscriber_id = %s;", userId);
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Post.class));
    }

    public List<Post> getPostsOfOtherUsers(Long userId) {
        String sql = String.format("select * from posts " +
                "where user_id != %s", userId);
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Post.class));
    }

    public void leaveCommentOnPost(Long postId, String comment) {
        String sql = String.format("insert into comments (text, date, post_id) " +
                "values ('%s', current_timestamp, %s);", comment, postId);
        jdbcTemplate.update(sql);
    }

    public void deleteCommentOnPost(Long userId, Long postId, Long commentId) {
        String sql = String.format("delete from comments " +
                "where id = (select * from comments as c " +
                "left join posts as p on c.post_id = p.id " +
                "left join users as u on p.user_id = u.id " +
                "where p.user_id = %s and c.post_id = %s and c.id = %s);", userId, postId, commentId);
        jdbcTemplate.update(sql);
    }

    public void leaveLikeUnderPost(Long userId, Long postId) {
        String sql = String.format("insert into likes (user_id, post_id, date) " +
                "values (%s, %s, current_timestamp);", userId, postId);
        jdbcTemplate.update(sql);
    }

    public void makePost(MultipartFile file, String description, Long userId) throws IOException {
        byte[] image = file.getBytes();
        String sql = "insert into posts (image, description, date, user_id) " +
                "values (?, ?, current_timestamp, ?)";
        jdbcTemplate.update(con -> {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setBytes(1, image);
            statement.setString(2, description);
            statement.setLong(3, userId);
            return statement;
        });
    }

    public Post getPictureOfPost(Long postId) {
        String sql = String.format("select image from posts where id = %s", postId);
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Post.class)).stream()
                .findFirst()
                .orElse(null);
    }
}
