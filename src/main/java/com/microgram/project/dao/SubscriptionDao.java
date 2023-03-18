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

    public void subscribe(Long subscriberId, Long subscribedToId) {
        String sql = String.format("insert into subscriptions(subscriber_id, subscribed_to_id, date) " +
                "values (%s, %s, current_date)", subscriberId, subscribedToId);
        String subscribeSql = String.format("update users set subs_qty = (select count(subscriber_id) from users as u " +
                "    left join subscriptions s on u.id = s.subscriber_id " +
                "    where u.id = %s " +
                "    group by u.id) " +
                "where id = %s;", subscriberId, subscriberId);
        String followedSql = String.format("update users set followers_qty = (select count(subscribed_to_id) from users as u " +
                "    left join subscriptions s on u.id = s.subscribed_to_id " +
                "    where u.id = %s " +
                "    group by u.id) " +
                "where id = %s;", subscribedToId, subscribedToId);
        jdbcTemplate.update(sql);
        jdbcTemplate.update(subscribeSql);
        jdbcTemplate.update(followedSql);
    }
}
