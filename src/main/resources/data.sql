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

INSERT INTO DISH (NAME, PRICE, RESTAURANT_ID)
VALUES ('Салат', 100, 1),
       ('Курица', 200, 1),
       ('Брускета', 150, 2),
       ('Салат', 200, 2),
       ('Десерт', 100, 2),
       ('Омлет', 100, 3);

INSERT INTO ITEM_MENU (DATE_MENU, DISH_ID)
VALUES (CURRENT_DATE, 1),
       (CURRENT_DATE, 3),
       (CURRENT_DATE, 4),
       ('1900-01-01', 5),
       ('1900-01-01', 6);

INSERT INTO VOTE (DATE_VOTE, USER_ID, RESTAURANT_ID)
VALUES (CURRENT_DATE, 2, 1);