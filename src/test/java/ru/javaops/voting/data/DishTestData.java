package ru.javaops.voting.data;

import ru.javaops.voting.model.Dish;

import java.time.LocalDate;
import java.util.List;

public class DishTestData {
    public static final int DISH_ID = 1;

    public static Dish dish1 = new Dish(DISH_ID, "Салат", 100.0, LocalDate.now());
    public static Dish dish2 = new Dish(DISH_ID + 1, "Курица", 200.0, LocalDate.now());
    public static Dish dish3 = new Dish(DISH_ID + 2, "Брускета", 150.0, LocalDate.now());
    public static Dish dish4 = new Dish(DISH_ID + 3, "Суп", 100.0, LocalDate.now());
    public static Dish dish5 = new Dish(DISH_ID + 4, "Десерт", 100.0, LocalDate.now().minusDays(1));
    public static Dish dish6 = new Dish(DISH_ID + 5, "Омлет", 100.0, LocalDate.now().minusDays(1));

    public static List<Dish> menu1 = List.of(dish1, dish2);
    public static List<Dish> menu2 = List.of(dish3, dish4, dish5);
    public static List<Dish> menu3 = List.of(dish6);

    public static Dish getNew() {
        return new Dish(null, "New", 300.0);
    }
}
