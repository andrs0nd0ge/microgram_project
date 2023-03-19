package com.microgram.project.util;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UtilityClass {
    private final JdbcTemplate jdbcTemplate;

    public String createUsersTable() {
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
        return "Everything went as it should've";
    }

    public String createSubscriptionsTable() {
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
        return "Everything went as it should've";
    }

    public String createPostsTable() {
        String sql = "create table if not exists posts " +
                "( " +
                "    id          bigserial " +
                "        primary key " +
                "        unique, " +
                "    image       bytea," +
                "    image_path  text, " +
                "    description text, " +
                "    date        timestamp, " +
                "    user_id     bigint " +
                "       constraint posts_user_fk " +
                "           references users " +
                "           on update cascade on delete cascade" +
                ");";
        jdbcTemplate.update(sql);
        return "Everything went as it should've";
    }

    public String createLikesTable() {
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
        return "Everything went as it should've";
    }

    public String createCommentsTable() {
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
        return "Everything went as it should've";
    }

    public String insertIntoUsers() {
        String sql = "INSERT INTO users (name, username, email, password, post_qty, subs_qty, followers_qty) " +
                "VALUES ('Max', 'd0ge', 'and.d0ge@gmail.com', '123d0ge123', 0, 0, 0), " +
                "('Jenson', 'j90', 'j90@gmail.com', 'j90123', 0, 0, 0), " +
                "('Michael', '96mic', 'mic_96@gmail.com', 'mic9090', 0, 0, 0);";
        jdbcTemplate.update(sql);
        return "Everything went as it should've";
    }

    public String insertIntoSubs() {
        String sql = "INSERT INTO subscriptions (subscriber_id, subscribed_to_id) " +
                "VALUES (1, 2), " +
                "(2, 3), " +
                "(3, 1)," +
                "(1, 3)," +
                "(3, 2);";
        jdbcTemplate.update(sql);
        return "Everything went as it should've";
    }

    public String insertIntoPosts() {
        String sql = "INSERT INTO posts (image, image_path, description, date, user_id) " +
                "VALUES ('first test picture', 'first test picture path', 'some description', current_timestamp, 3)," +
                "('second test picture', 'second test picture path', 'another description', current_timestamp, 2)," +
                "('third test picture', 'third test picture path', 'some other description', current_timestamp, 2)";
        jdbcTemplate.update(sql);
        return "Everything went as it should've";
    }

    public String insertIntoLikes() {
        String sql = "INSERT INTO likes (user_id, post_id) " +
                "VALUES (1, 1)," +
                "(3, 1)," +
                "(2, 2)";
        jdbcTemplate.update(sql);
        return "Everything went as it should've";
    }

    public String insertIntoComments() {
        String sql = "INSERT INTO comments (text, date, post_id) " +
                "VALUES ('some text', current_timestamp, 2)," +
                "('another text', current_timestamp, 3)," +
                "('some other text', current_timestamp, 1)," +
                "('text', current_timestamp, 2)";
        jdbcTemplate.update(sql);
        return "Everything went as it should've";
    }

    public String updateUsers() {
        String postsForDoge = "update users set post_qty = (select count(user_id) from users as u " +
                "    left join posts p on u.id = p.user_id " +
                "    where u.id = 1 " +
                "    group by u.id) " +
                "where id = 1;";
        String postsForJ90 = "update users set post_qty = (select count(user_id) from users as u " +
                "    left join posts p on u.id = p.user_id " +
                "    where u.id = 2 " +
                "    group by u.id) " +
                "where id = 2;";
        String postsFor96mic = "update users set post_qty = (select count(user_id) from users as u " +
                "    left join posts p on u.id = p.user_id " +
                "    where u.id = 3 " +
                "    group by u.id) " +
                "where id = 3;";
        String subsForDoge = "update users set subs_qty = (select count(subscriber_id) from users as u " +
                "    left join subscriptions s on u.id = s.subscriber_id " +
                "    where u.id = 1 " +
                "    group by u.id) " +
                "where id = 1;";
        String subsForJ90 = "update users set subs_qty = (select count(subscriber_id) from users as u " +
                "    left join subscriptions s on u.id = s.subscriber_id " +
                "    where u.id = 2 " +
                "    group by u.id) " +
                "where id = 2;";
        String subsFor96mic = "update users set subs_qty = (select count(subscriber_id) from users as u " +
                "    left join subscriptions s on u.id = s.subscriber_id " +
                "    where u.id = 3 " +
                "    group by u.id) " +
                "where id = 3;";
        String followersForDoge = "update users set followers_qty = (select count(subscribed_to_id) from users as u " +
                "    left join subscriptions s on u.id = s.subscribed_to_id " +
                "    where u.id = 1 " +
                "    group by u.id) " +
                "where id = 1;";
        String followersForJ90 = "update users set followers_qty = (select count(subscribed_to_id) from users as u " +
                "    left join subscriptions s on u.id = s.subscribed_to_id " +
                "    where u.id = 2 " +
                "    group by u.id) " +
                "where id = 2;";
        String followersFor96mic = "update users set followers_qty = (select count(subscribed_to_id) from users as u " +
                "    left join subscriptions s on u.id = s.subscribed_to_id " +
                "    where u.id = 3 " +
                "    group by u.id) " +
                "where id = 3;";
        jdbcTemplate.update(postsForDoge);
        jdbcTemplate.update(postsForJ90);
        jdbcTemplate.update(postsFor96mic);
        jdbcTemplate.update(subsForDoge);
        jdbcTemplate.update(subsForJ90);
        jdbcTemplate.update(subsFor96mic);
        jdbcTemplate.update(followersForDoge);
        jdbcTemplate.update(followersForJ90);
        jdbcTemplate.update(followersFor96mic);
        return "Everything went as it should've";
    }
}
