DELETE FROM task;
DELETE FROM category;
DELETE FROM priority;
DELETE FROM roles;
DELETE FROM users;

INSERT INTO users (id, name, email, password, registered, completed_total, uncompleted_total)
VALUES (1, 'User', 'user@yandex.ru', 'password', '2020-04-29 15:27:11', 0, 0),
       (2, 'Admin', 'admin@gmail.com', 'admin', '2020-04-29 15:27:11', 0, 0);

INSERT INTO roles (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);



INSERT INTO  category (id, user_id, title, completed_count, uncompleted_count)
VALUES (3, 1, 'Family', 0, 0),
       (4, 1, 'Work', 0, 0),
       (5, 2, 'Chill', 0, 0),
       (6, 2, 'Travel', 0, 0);


INSERT INTO  priority (id, user_id, title, color)
VALUES (7, 1, 'Low', '#caffdd'),
       (8, 1, 'Middle', '#883bdc'),
       (9, 2,  'High', '#f05f5f'),
       (10, 2,  'Very high', '#f05f5f');


INSERT INTO  task (id, user_id, title, completed, date, priority_id, category_id)
VALUES (11, 1, 'Buy beer', 1, '2018-04-29 15:27:11', 7, 3),
       (12, 1, 'World var', 1, '2019-04-30 09:38:39', 8, NULL),
       (13, 1, 'Pray to doshirak gods', 0, '2020-04-27 15:27:34', 7, 4),
       (14, 1, 'Kill Bill', 0, '2021-04-27 15:27:34', 8, 4),
       (15, 2, 'Make my cat bold', 1, '2018-04-28 07:03:03', 9, NULL),
       (16, 2, 'Make my dog longhair', 1, '2019-04-28 07:03:03', 10, NULL),
       (17, 2, 'Ride a car', 0, '2020-05-06 09:38:23', 9, 5),
       (18, 2, 'Keep calm', 0, '2021-05-01 12:01:18', 10, 6);
