package com.microgram.project.mapper;

import com.microgram.project.entity.Comment;
import com.microgram.project.entity.Post;
import com.microgram.project.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class CommentPostUserRowMapper implements RowMapper<Comment> {
    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Comment comment = new Comment();
        comment.setId(rs.getLong("comment_id"));
        comment.setText(rs.getString("text"));
        comment.setDate(rs.getObject("comment_date", LocalDateTime.class));

        Post post = new Post();
        post.setId(rs.getLong("post_id"));
        post.setImageName(rs.getString("image_name"));
        post.setDescription(rs.getString("description"));
        post.setDate(rs.getObject("post_date", LocalDateTime.class));

        comment.setPost(post);

        User user = new User();
        user.setId(rs.getLong("user_id"));
        user.setName(rs.getString("name"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));

        comment.setUser(user);

        return comment;
    }
}
