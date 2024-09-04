create schema if not exists microgram;

create table if not exists microgram.users
(
    id            bigserial
        primary key
        unique,
    name          varchar(60),
    username      varchar(70)  not null
        unique,
    email         varchar(100) not null
        unique,
    password      text         not null,
    post_qty      int default 0,
    subs_qty      int default 0,
    followers_qty int default 0
);

alter table microgram.users
    owner to postgres;

create unique index if not exists users_id_uindex
    on microgram.users (id);



create table if not exists microgram.subscriptions
(
    subscriber_id    bigint,
    subscribed_to_id bigint,
    date             date default current_date,
    primary key (subscriber_id, subscribed_to_id),
    foreign key (subscriber_id)
        references microgram.users (id)
        on update cascade on delete cascade,
    foreign key (subscribed_to_id)
        references microgram.users (id)
        on update cascade on delete cascade
);

alter table microgram.subscriptions
    owner to postgres;



create table if not exists microgram.posts
(
    id          bigserial
        primary key
        unique,
    image       bytea,
    image_name  text,
    description text,
    date        timestamp,
    user_id     bigint
        constraint posts_user_fk
            references microgram.users
            on update cascade on delete cascade
);

alter table microgram.posts
    owner to postgres;

create unique index if not exists posts_id_uindex
    on microgram.posts (id);



create table if not exists microgram.likes
(
    user_id bigint,
    post_id bigint,
    date    timestamp default current_timestamp,
    primary key (user_id, post_id),
    foreign key (user_id)
        references microgram.users (id)
        on update cascade on delete cascade,
    foreign key (post_id)
        references microgram.posts (id)
        on update cascade on delete cascade
);

alter table microgram.likes
    owner to postgres;



create table if not exists microgram.comments
(
    id      bigserial
        primary key
        unique,
    text    text,
    date    timestamp,
    post_id bigint
        constraint comments_post_fk
            references microgram.posts (id)
            on update cascade on delete cascade,
    user_id bigint
        constraint comments_user_fk
            references microgram.users (id)
            on update cascade on delete cascade
);

alter table microgram.comments
    owner to postgres;

create unique index if not exists comments_id_uindex
    on microgram.comments (id);