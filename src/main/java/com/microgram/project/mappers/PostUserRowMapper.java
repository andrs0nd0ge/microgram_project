package com.microgram.project.mappers;

import com.microgram.project.entity.Post;
import com.microgram.project.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class PostUserRowMapper implements RowMapper<Post> {
    @Override
    public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
        Post post = new Post();
        post.setId(rs.getLong("post_id"));
        post.setImageName(rs.getString("image_name"));
        post.setDescription(rs.getString("description"));
        post.setDate(rs.getObject("date", LocalDateTime.class));

        User user = new User();
        user.setId(rs.getLong("user_id"));
        user.setName(rs.getString("name"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));

        post.setUser(user);

        return post;
    }
}
