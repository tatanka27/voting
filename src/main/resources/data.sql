INSERT INTO USERS (NAME, EMAIL, PASSWORD)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO USER_ROLE (ROLE, USER_ID)
VALUES ('USER', 1),
       ('ADMIN', 2);

INSERT INTO RESTAURANT (NAME)
VALUES ('Трезвая утка'),
       ('Сытый гусь'),
       ('Вкусно и точка'),
       ('Теремок');

INSERT INTO DISH (NAME, PRICE)
VALUES ('Салат', 100),
       ('Курица', 200),
       ('Брускета', 150),
       ('Суп', 100),
       ('Десерт', 100),
       ('Омлет', 100);

INSERT INTO MENU (DATE_MENU, RESTAURANT_ID, DISH_ID)
VALUES (convert(now(), date), 1, 1),
       (convert(now(), date), 1, 2),
       (convert(now(), date), 2, 3),
       (convert(now(), date), 2, 4),
       (convert('1900-01-01', date), 2, 5),
       (convert('1900-01-01', date), 3, 6);
