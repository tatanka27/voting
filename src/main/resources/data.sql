INSERT INTO USERS (NAME, EMAIL, PASSWORD)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO USER_ROLE (ROLE, USER_ID)
VALUES ('USER', 1),
       ('ADMIN', 2);

INSERT INTO RESTAURANT (NAME)
VALUES ('Трезвая утка'),
       ('Сытый гусь');

INSERT INTO MEAL (NAME, DATE_TIME, REST_ID)
VALUES ('Салат', now(), 1),
       ('Курица', now(), 1),
       ('Брускета', now(), 2),
       ('Суп', now(), 2);
