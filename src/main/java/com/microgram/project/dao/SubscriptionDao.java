package com.microgram.project.dao;

import com.microgram.project.entity.Subscription;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SubscriptionDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Subscription> getAllSubscriptions() {
        String sql = "select * from subscriptions";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Subscription.class));
    }

    public void updateSubsQty(Long userId) {
        String sql = String.format("update users set subs_qty = (select count(subscriber_id) from users as u " +
                "    left join subscriptions s on u.id = s.subscriber_id " +
                "    where u.id = %s " +
                "    group by u.id) " +
                "where id = %s;", userId, userId);
        jdbcTemplate.update(sql);
    }

    public void updateFollowersQty(Long userId) {
        String sql = String.format("update users set followers_qty = (select count(subscribed_to_id) from users as u " +
                "    left join subscriptions s on u.id = s.subscribed_to_id " +
                "    where u.id = %s " +
                "    group by u.id) " +
                "where id = %s;", userId, userId);
        jdbcTemplate.update(sql);
    }

    public void subscribe(Long subscriberId, Long subscribedToId) {
        String sql = String.format("insert into subscriptions(subscriber_id, subscribed_to_id, date) " +
                "values (%s, %s, current_date)", subscriberId, subscribedToId);
        jdbcTemplate.update(sql);
        updateSubsQty(subscriberId);
        updateFollowersQty(subscribedToId);
    }
}
