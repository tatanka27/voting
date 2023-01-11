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

INSERT INTO ITEM (NAME, PRICE, DATE_MENU, RESTAURANT_ID)
VALUES ('Салат', 100, now(), 1),
       ('Курица', 200, now(), 1),
       ('Брускета', 150, now(), 2),
       ('Суп', 100, now(), 2),
       ('Десерт', 100, convert('1900-01-01', date), 2),
       ('Омлет', 100, convert('1900-01-01', date), 3);

INSERT INTO VOTE (DATE_VOTE, REST_ID, USER_ID)
VALUES (now(), 1, 1),
       (now(), 2, 2);
