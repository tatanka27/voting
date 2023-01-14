## Graduation project [TopJava](https://javaops.ru/view/topjava)

Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) **without frontend**.

The task is:

Build a voting system for deciding where to have lunch.

* 2 types of users: admin and regular users
* Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
* Menu changes each day (admins do the updates)
* Users can vote for a restaurant they want to have lunch at today
* Only one vote counted per user
* If user votes again the same day:
    - If it is before 11:00 we assume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides a new menu each day.

# REST API

Specification API [openapi.yaml](openapi.yaml).

[Swagger](http://localhost:8080/)

Test data:
* user@yandex.ru / password
* admin@gmail.com / admin

# Request examples

## User registration

```console
curl -X 'POST' 'http://localhost:8080/api/register' -H 'accept: application/json' -H 'Content-Type: application/json' -d '{"name": "name","email": "test@test.ru","password": "123456"}'  
```

## Request ADMIN: admin@gmail.com / admin

### Get all restaurants

``` console
curl -X 'GET' 'http://localhost:8080/api/admin/restaurants' -H 'accept: application/json' --user admin@gmail.com:admin
```

### Get a restaurant by id

``` console
curl -X 'GET' 'http://localhost:8080/api/admin/restaurants/1' -H 'accept: application/json' --user admin@gmail.com:admin
```

### Get a restaurant by id with a menu

``` console
curl -X 'GET' 'http://localhost:8080/api/admin/restaurants/1/with-menu' -H 'accept: application/json' --user admin@gmail.com:admin
```

### Add a restaurant

``` console
curl -X 'POST' 'http://localhost:8080/api/admin/restaurants' -H 'accept: application/json' -H 'Content-Type: application/json' -d '{"name": "Новый ресторан"}' --user admin@gmail.com:admin
```

### Get all dishes

``` console
curl -X 'POST' 'http://localhost:8080/api/admin/dishes' -H 'accept: application/json' --user admin@gmail.com:admin
```

### Get a dish by id

``` console
curl -X 'POST' 'http://localhost:8080/api/admin/dishes' -H 'accept: application/json' --user admin@gmail.com:admin
```

### Add a dish

``` console
curl -X 'POST' 'http://localhost:8080/api/admin/dishes' -H 'accept: application/json' -H 'Content-Type: application/json' -d '{"name": "Новое блюдл", "price": "300"}' --user admin@gmail.com:admin
```

### Add a menu item for a restaurant for today

``` console
curl -X 'POST' 'http://localhost:8080/api/admin/menu' -H 'accept: application/json' -H 'Content-Type: application/json' -d '{"restaurantId": "1", "dishId": "8"}' --user admin@gmail.com:admin
```

## Request for USER: user@yandex.ru / password

### Get all restaurants

``` console
curl -X 'GET' 'http://localhost:8080/api/restaurants' -H 'accept: application/json' --user user@yandex.ru:password
```

### Get a restaurant by id

``` console
curl -X 'GET' 'http://localhost:8080/api/restaurants/1' -H 'accept: application/json' --user user@yandex.ru:password
```

### Get a restaurant by id with a menu

``` console
curl -X 'GET' 'http://localhost:8080/api/restaurants/1/with-menu' -H 'accept: application/json' --user user@yandex.ru:password
```

### Vote for a restaurant

``` console
curl -X 'POST' 'http://localhost:8080/api/vote' -H 'accept: application/json' -H 'Content-Type: application/json' -d '{"restaurantId": "4"}' --user user@yandex.ru:password
```

# Caveats

If your TimeZone +3 then this app won't work properly between 00 and 03 a.m.