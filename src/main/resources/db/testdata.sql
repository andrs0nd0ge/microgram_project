start transaction;

INSERT INTO microgram.users (name, username, email, password, post_qty, subs_qty, followers_qty)
VALUES ('First', 'first', 'onetest@test', '123', 0, 0, 0),
       ('Second', 'second', 'twotest@test', '123', 0, 0, 0),
       ('Third', 'third', 'threetest@test', '123', 0, 0, 0);

INSERT INTO microgram.subscriptions (subscriber_id, subscribed_to_id)
VALUES (1, 2),
       (2, 3),
       (3, 1),
       (1, 3),
       (3, 2);

INSERT INTO microgram.posts (image, image_name, description, date, user_id)
VALUES ('first test picture', 'first test picture name', 'some description', current_timestamp, 3),
       ('second test picture', 'second test picture name', 'another description', current_timestamp, 2),
       ('third test picture', 'third test picture name', 'some other description', current_timestamp, 2);

INSERT INTO microgram.likes (user_id, post_id)
VALUES (1, 1),
       (3, 1),
       (2, 2);

INSERT INTO microgram.comments (text, date, post_id, user_id)
VALUES ('some text', current_timestamp, 2, 1),
       ('another text', current_timestamp, 3, 3),
       ('some other text', current_timestamp, 1, 2),
       ('text', current_timestamp, 2, 2);

update microgram.users
set post_qty = (select count(user_id)
                from microgram.users as u
                         left join microgram.posts p on u.id = p.user_id
                where u.id = 1
                group by u.id)
where id = 1;

update microgram.users
set post_qty = (select count(user_id)
                from microgram.users as u
                         left join microgram.posts p on u.id = p.user_id
                where u.id = 2
                group by u.id)
where id = 2;

update microgram.users
set post_qty = (select count(user_id)
                from microgram.users as u
                         left join microgram.posts p on u.id = p.user_id
                where u.id = 3
                group by u.id)
where id = 3;

update microgram.users
set subs_qty = (select count(subscriber_id)
                from microgram.users as u
                         left join microgram.subscriptions s on u.id = s.subscriber_id
                where u.id = 1
                group by u.id)
where id = 1;

update microgram.users
set subs_qty = (select count(subscriber_id)
                from microgram.users as u
                         left join microgram.subscriptions s on u.id = s.subscriber_id
                where u.id = 2
                group by u.id)
where id = 2;

update microgram.users
set subs_qty = (select count(subscriber_id)
                from microgram.users as u
                         left join microgram.subscriptions s on u.id = s.subscriber_id
                where u.id = 3
                group by u.id)
where id = 3;

update microgram.users
set followers_qty = (select count(subscribed_to_id)
                     from microgram.users as u
                              left join microgram.subscriptions s on u.id = s.subscribed_to_id
                     where u.id = 1
                     group by u.id)
where id = 1;

update microgram.users
set followers_qty = (select count(subscribed_to_id)
                     from microgram.users as u
                              left join microgram.subscriptions s on u.id = s.subscribed_to_id
                     where u.id = 2
                     group by u.id)
where id = 2;

update microgram.users
set followers_qty = (select count(subscribed_to_id)
                     from microgram.users as u
                              left join microgram.subscriptions s on u.id = s.subscribed_to_id
                     where u.id = 3
                     group by u.id)
where id = 3;

commit;