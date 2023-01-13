package ru.javaops.voting.data;

import ru.javaops.voting.model.Dish;
import ru.javaops.voting.web.MatcherFactory;

import java.util.List;

public class DishTestData {

    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER =
            MatcherFactory.usingEqualsComparator(Dish.class);

    public static final int DISH1_ID = 1;
    public static final int DISH2_ID = 2;
    public static final int DISH3_ID = 3;
    public static final int DISH4_ID = 4;
    public static final int DISH5_ID = 5;
    public static final int DISH6_ID = 6;
    public static final int DISH_NOT_FOUND_ID = 100;

    public static Dish dish1 = new Dish(DISH1_ID, "Салат", 100.0);
    public static Dish dish2 = new Dish(DISH2_ID, "Курица", 200.0);
    public static Dish dish3 = new Dish(DISH3_ID, "Брускета", 150.0);
    public static Dish dish4 = new Dish(DISH4_ID, "Суп", 100.0);
    public static Dish dish5 = new Dish(DISH5_ID, "Десерт", 100.0);
    public static Dish dish6 = new Dish(DISH6_ID, "Омлет", 100.0);

    public static List<Dish> dishes = List.of(dish3, dish5, dish2, dish6, dish1, dish4);

    public static Dish getNew() {
        return new Dish(null, "New", 300.0);
    }
}
