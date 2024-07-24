package com.microgram.project.util;

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
        user.setPassword(rs.getString("password"));
        user.setPostQty(rs.getInt("post_qty"));
        user.setSubsQty(rs.getInt("subs_qty"));
        user.setFollowersQty(rs.getInt("followers_qty"));

        post.setUser(user);

        return post;
    }
}
