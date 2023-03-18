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
                "    username      varchar(70) not null, " +
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
                "    id               bigserial " +
                "        primary key " +
                "        unique, " +
                "    subscriber_id    int " +
                "        constraint subscriptions_subscriber_fk " +
                "            references users " +
                "            on update cascade on delete cascade, " +
                "    subscribed_to_id int " +
                "        constraint subscriptions_subscribed_to_fk " +
                "            references users " +
                "            on update cascade on delete cascade, " +
                "    date             date " +
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
                "    image       text, " +
                "    description text, " +
                "    date        timestamp, " +
                "    user_id     int " +
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
                "    id      bigserial " +
                "        primary key " +
                "        unique, " +
                "    user_id int " +
                "        constraint likes_user_fk " +
                "            references users " +
                "            on update cascade on delete cascade, " +
                "    post_id int " +
                "        constraint likes_post_fk " +
                "            references posts (id) " +
                "            on update cascade on delete cascade, " +
                "    date    timestamp default current_timestamp" +
                ");";
        jdbcTemplate.update(sql);
        return "Everything went as it should've";
    }

    public String createCommentsTable() {
        String sql = "create table if not exists comments " +
                "( " +
                "    id   bigserial " +
                "        primary key " +
                "        unique, " +
                "    text text, " +
                "    date timestamp, " +
                "    post_id int" +
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
        String sql = "INSERT INTO subscriptions (subscriber_id, subscribed_to_id, date) " +
                "VALUES (1, 2, current_date), " +
                "(2, 3, current_date), " +
                "(3, 1, current_date)," +
                "(1, 3, current_date);";
        jdbcTemplate.update(sql);
        return "Everything went as it should've";
    }

    public String insertIntoPosts() {
        String sql = "INSERT INTO posts (image, description, date, user_id) " +
                "VALUES ('first test picture', 'some description', current_timestamp, 3)," +
                "('second test picture', 'another description', current_timestamp, 2)," +
                "('third test picture', 'some other description', current_timestamp, 2)";
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
                "('some other text', current_timestamp, 1)";
        jdbcTemplate.update(sql);
        return "Everything went as it should've";
    }
}
