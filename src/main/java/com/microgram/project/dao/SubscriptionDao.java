package com.microgram.project.dao;

import com.microgram.project.entity.Subscription;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SubscriptionDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    public List<Subscription> getAllSubscriptions() {
        String sql = "select * from microgram.subscriptions";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Subscription.class));
    }

    public void updateSubsQty(Long userId) {
        String sql = "update microgram.users set subs_qty = " +
                "(select count(subscriber_id) from users as u " +
                "    left join microgram.subscriptions s on u.id = s.subscriber_id " +
                "    where u.id = :userId " +
                "    group by u.id) " +
                "where id = :userId";
        namedJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("userId", userId));
    }

    public void updateFollowersQty(Long userId) {
        String sql = "update microgram.users set followers_qty = " +
                "(select count(subscribed_to_id) from users as u " +
                "    left join microgram.subscriptions s on u.id = s.subscribed_to_id " +
                "    where u.id = :userId " +
                "    group by u.id) " +
                "where id = :userId";
        namedJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("userId", userId));
    }

    public void subscribe(Long subscriberId, Long subscribedToId) {
        String sql = "insert into microgram.subscriptions(subscriber_id, subscribed_to_id, date) " +
                "values (:subId, :subbedToId, current_date)";
        namedJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("subId", subscriberId)
                .addValue("subbedToId", subscribedToId));
        updateSubsQty(subscriberId);
        updateFollowersQty(subscribedToId);
    }

    public void unsubscribe(Long subscriberId, Long subscribedToId) {
        String sql = "delete from microgram.subscriptions " +
                "where subscriber_id = :subId and subscribed_to_id = :subbedToId";
        namedJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("subId", subscriberId)
                .addValue("subbedToId", subscribedToId));
        updateSubsQty(subscriberId);
        updateFollowersQty(subscribedToId);
    }
}
