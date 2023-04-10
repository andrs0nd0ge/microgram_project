package com.microgram.project.util;

import com.microgram.project.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;

@Component
@AllArgsConstructor
public class UtilityClass {
    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;

    public void createUsersTable() {
        String sql = "create table if not exists users " +
                "( " +
                "    id            bigserial " +
                "        primary key " +
                "        unique, " +
                "    name          varchar(60), " +
                "    username      varchar(70) not null " +
                "        unique, " +
                "    email         varchar(100) not null " +
                "        unique, " +
                "    password      text        not null, " +
                "    post_qty      int default 0, " +
                "    subs_qty      int default 0, " +
                "    followers_qty int default 0 " +
                ");";
        jdbcTemplate.update(sql);
    }

    public void createSubscriptionsTable() {
        String sql = "create table if not exists subscriptions " +
                "( " +
                "    subscriber_id    bigint, " +
                "    subscribed_to_id bigint, " +
                "    date             date default current_date, " +
                "    primary key (subscriber_id, subscribed_to_id), " +
                "    foreign key (subscriber_id) " +
                "        references users (id) " +
                "            on update cascade on delete cascade, " +
                "    foreign key (subscribed_to_id) " +
                "        references users (id) " +
                "            on update cascade on delete cascade" +
                ");";
        jdbcTemplate.update(sql);
    }

    public void createPostsTable() {
        String sql = "create table if not exists posts " +
                "( " +
                "    id          bigserial " +
                "        primary key " +
                "        unique, " +
                "    image       bytea," +
                "    image_name  text, " +
                "    description text, " +
                "    date        timestamp, " +
                "    user_id     bigint " +
                "       constraint posts_user_fk " +
                "           references users " +
                "           on update cascade on delete cascade" +
                ");";
        jdbcTemplate.update(sql);
    }

    public void createLikesTable() {
        String sql = "create table if not exists likes " +
                "( " +
                "    user_id    bigint, " +
                "    post_id    bigint, " +
                "    date       timestamp default current_timestamp, " +
                "    primary key (user_id, post_id), " +
                "    foreign key (user_id) " +
                "        references users (id) " +
                "        on update cascade on delete cascade, " +
                "    foreign key (post_id) " +
                "        references posts (id) " +
                "        on update cascade on delete cascade " +
                ");";
        jdbcTemplate.update(sql);
    }

    public void createCommentsTable() {
        String sql = "create table if not exists comments " +
                "( " +
                "    id   bigserial " +
                "         primary key " +
                "         unique, " +
                "    text text, " +
                "    date timestamp, " +
                "    post_id bigint" +
                "       constraint comments_post_fk" +
                "           references posts(id)" +
                "           on update cascade on delete cascade" +
                ");";
        jdbcTemplate.update(sql);
    }
    public void insertIntoUsers() {
        User first = new User();
        first.setPassword(passwordEncoder.encode("123"));
        User second = new User();
        second.setPassword(passwordEncoder.encode("123"));
        User third = new User();
        third.setPassword(passwordEncoder.encode("123"));
        String sql = "INSERT INTO users (name, username, email, password, post_qty, subs_qty, followers_qty) " +
                "VALUES ('First', 'first', 'onetest@test', ?, 0, 0, 0), " +
                "('Second', 'second', 'twotest@test', ?, 0, 0, 0), " +
                "('Third', 'third', 'threetest@test', ?, 0, 0, 0);";
        jdbcTemplate.update(con -> {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, first.getPassword());
            statement.setString(2, second.getPassword());
            statement.setString(3, third.getPassword());
            return statement;
        });
    }

    public void insertIntoSubs() {
        String sql = "INSERT INTO subscriptions (subscriber_id, subscribed_to_id) " +
                "VALUES (1, 2), " +
                "(2, 3), " +
                "(3, 1)," +
                "(1, 3)," +
                "(3, 2);";
        jdbcTemplate.update(sql);
    }

    public void insertIntoPosts() {
        String sql = "INSERT INTO posts (image, image_name, description, date, user_id) " +
                "VALUES ('first test picture', 'first test picture name', 'some description', current_timestamp, 3)," +
                "('second test picture', 'second test picture name', 'another description', current_timestamp, 2)," +
                "('third test picture', 'third test picture name', 'some other description', current_timestamp, 2)";
        jdbcTemplate.update(sql);
    }

    public void insertIntoLikes() {
        String sql = "INSERT INTO likes (user_id, post_id) " +
                "VALUES (1, 1)," +
                "(3, 1)," +
                "(2, 2)";
        jdbcTemplate.update(sql);
    }

    public void insertIntoComments() {
        String sql = "INSERT INTO comments (text, date, post_id) " +
                "VALUES ('some text', current_timestamp, 2)," +
                "('another text', current_timestamp, 3)," +
                "('some other text', current_timestamp, 1)," +
                "('text', current_timestamp, 2)";
        jdbcTemplate.update(sql);
    }

    public void updateUsers() {
        String postsForFirst = "update users set post_qty = (select count(user_id) from users as u " +
                "    left join posts p on u.id = p.user_id " +
                "    where u.id = 1 " +
                "    group by u.id) " +
                "where id = 1;";
        String postsForSecond = "update users set post_qty = (select count(user_id) from users as u " +
                "    left join posts p on u.id = p.user_id " +
                "    where u.id = 2 " +
                "    group by u.id) " +
                "where id = 2;";
        String postsForThird = "update users set post_qty = (select count(user_id) from users as u " +
                "    left join posts p on u.id = p.user_id " +
                "    where u.id = 3 " +
                "    group by u.id) " +
                "where id = 3;";
        String subsForFirst = "update users set subs_qty = (select count(subscriber_id) from users as u " +
                "    left join subscriptions s on u.id = s.subscriber_id " +
                "    where u.id = 1 " +
                "    group by u.id) " +
                "where id = 1;";
        String subsForSecond = "update users set subs_qty = (select count(subscriber_id) from users as u " +
                "    left join subscriptions s on u.id = s.subscriber_id " +
                "    where u.id = 2 " +
                "    group by u.id) " +
                "where id = 2;";
        String subsForThird = "update users set subs_qty = (select count(subscriber_id) from users as u " +
                "    left join subscriptions s on u.id = s.subscriber_id " +
                "    where u.id = 3 " +
                "    group by u.id) " +
                "where id = 3;";
        String followersForFirst = "update users set followers_qty = (select count(subscribed_to_id) from users as u " +
                "    left join subscriptions s on u.id = s.subscribed_to_id " +
                "    where u.id = 1 " +
                "    group by u.id) " +
                "where id = 1;";
        String followersForSecond = "update users set followers_qty = (select count(subscribed_to_id) from users as u " +
                "    left join subscriptions s on u.id = s.subscribed_to_id " +
                "    where u.id = 2 " +
                "    group by u.id) " +
                "where id = 2;";
        String followersForThird = "update users set followers_qty = (select count(subscribed_to_id) from users as u " +
                "    left join subscriptions s on u.id = s.subscribed_to_id " +
                "    where u.id = 3 " +
                "    group by u.id) " +
                "where id = 3;";
        jdbcTemplate.update(postsForFirst);
        jdbcTemplate.update(postsForSecond);
        jdbcTemplate.update(postsForThird);
        jdbcTemplate.update(subsForFirst);
        jdbcTemplate.update(subsForSecond);
        jdbcTemplate.update(subsForThird);
        jdbcTemplate.update(followersForFirst);
        jdbcTemplate.update(followersForSecond);
        jdbcTemplate.update(followersForThird);
    }
}
