package com.microgram.project.util;

import com.microgram.project.entity.*;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UtilityClass {
    private final JdbcTemplate jdbcTemplate;

    public void createUsersTable() {
        String sql = "create table public.users\n" +
                "(\n" +
                "    id            bigserial\n" +
                "        primary key\n" +
                "        unique,\n" +
                "    name          varchar(60),\n" +
                "    username      varchar(70) not null,\n" +
                "    email         varchar(100) not null\n" +
                "        unique,\n" +
                "    password      text        not null,\n" +
                "    post_qty      int default 0,\n" +
                "    subs_qty      int default 0,\n" +
                "    followers_qty int default 0\n" +
                ");";
        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public void createSubscriptionsTable() {
        String sql = "create table public.subscriptions\n" +
                "(\n" +
                "    id               bigserial\n" +
                "        primary key\n" +
                "        unique,\n" +
                "    subscriber_id    int\n" +
                "        constraint subscriptions_subscriber_fk\n" +
                "            references public.users\n" +
                "            on update cascade on delete cascade,\n" +
                "    subscribed_to_id int\n" +
                "        constraint subscriptions_subscribed_to_fk\n" +
                "            references public.users\n" +
                "            on update cascade on delete cascade,\n" +
                "    date             date\n" +
                ");";
        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Subscription.class));
    }

    public void createPostsTable() {
        String sql = "create table public.posts\n" +
                "(\n" +
                "    id          bigserial\n" +
                "        primary key\n" +
                "        unique,\n" +
                "    image       text,\n" +
                "    description text,\n" +
                "    date        timestamp,\n" +
                "    user_id     int\n" +
                "       constraint posts_user_fk\n" +
                "           references public.users\n" +
                "           on update cascade on delete cascade" +
                ");";
        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Post.class));
    }

    public void createLikesTable() {
        String sql = "create table public.likes\n" +
                "(\n" +
                "    id      bigserial\n" +
                "        primary key\n" +
                "        unique,\n" +
                "    user_id int\n" +
                "        constraint likes_user_fk\n" +
                "            references public.users\n" +
                "            on update cascade on delete cascade,\n" +
                "    post_id int\n" +
                "        constraint likes_post_fk\n" +
                "            references public.posts (id)\n" +
                "            on update cascade on delete cascade,\n" +
                "    date    timestamp\n" +
                ");";
        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Like.class));
    }

    public void createCommentsTable() {
        String sql = "create table public.comments\n" +
                "(\n" +
                "    id   bigserial\n" +
                "        primary key\n" +
                "        unique,\n" +
                "    text text,\n" +
                "    date timestamp,\n" +
                "    post_id int" +
                "       constraint comments_post_fk" +
                "           references public.posts(id)" +
                "           on update cascade on delete cascade" +
                ");";
        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Comment.class));
    }

    public void insertIntoUsers() {
        String sql = "INSERT INTO public.users (name, username, email, password, post_qty, subs_qty, followers_qty) " +
                "VALUES ('Max', 'd0ge', 'and.d0ge@gmail.com', '123d0ge123', 0, 0, 0), " +
                "('Jenson', 'j90', 'j90@gmail.com', 'j90123', 0, 0, 0), " +
                "('Michael', '96mic', 'mic_96@gmail.com', 'mic9090', 0, 0, 0);";
        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public void insertIntoSubs() {
        String sql = "INSERT INTO public.subscriptions (subscriber_id, subscribed_to_id, date) " +
                "VALUES (1, 2, current_date), " +
                "(2, 3, current_date), " +
                "(3, 1, current_date);";
        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Subscription.class));
    }

    public void insertIntoPosts() {
        String sql = "INSERT INTO public.posts (image, description, date, user_id) " +
                "VALUES ('first test picture', 'some description', current_date, 3)," +
                "('second test picture', 'another description', current_date, 2)," +
                "('third test picture', 'some other description', current_date, 2)";
        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Post.class));
    }

    public void insertIntoLikes() {
        String sql = "INSERT INTO public.likes (user_id, post_id) " +
                "VALUES (1, 1)," +
                "(3, 1)," +
                "(2, 2)";
        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Like.class));
    }

    public void insertIntoComments() {
        String sql = "INSERT INTO public.comments (text, date, post_id) " +
                "VALUES ('some text', current_date, 2)," +
                "('another text', current_date, 3)," +
                "('some other text', current_date, 1)";
        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Comment.class));
    }
}
