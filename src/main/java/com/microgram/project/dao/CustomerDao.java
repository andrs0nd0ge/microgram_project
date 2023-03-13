package com.microgram.project.dao;

import com.microgram.project.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CustomerDao {
    private final JdbcTemplate jdbcTemplate;

    public List<User> customerList() {
        String sql = "select * from customers";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }
}
