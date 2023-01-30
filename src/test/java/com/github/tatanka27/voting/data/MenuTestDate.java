package com.github.tatanka27.voting.data;

import com.github.tatanka27.voting.model.Menu;
import com.github.tatanka27.voting.web.MatcherFactory;

import java.time.LocalDate;
import java.util.List;

public class MenuTestDate {

    public static final MatcherFactory.Matcher<Menu> MENU_MATCHER =
            MatcherFactory.usingEqualsComparator(Menu.class);

    public static Menu menu1 = new Menu(1, LocalDate.now(), RestaurantTestData.restaurant1, DishTestData.dish1);
    public static Menu menu2 = new Menu(2, LocalDate.now(), RestaurantTestData.restaurant1, DishTestData.dish2);
    public static Menu menu3 = new Menu(3, LocalDate.now(), RestaurantTestData.restaurant2, DishTestData.dish3);
    public static Menu menu4 = new Menu(4, LocalDate.now(), RestaurantTestData.restaurant2, DishTestData.dish4);
    public static Menu menu5 = new Menu(5, LocalDate.of(1900, 1, 1), RestaurantTestData.restaurant2, DishTestData.dish5);
    public static Menu menu6 = new Menu(6, LocalDate.of(1900, 1, 1), RestaurantTestData.restaurant3, DishTestData.dish6);

    public static List<Menu> menus1 = List.of(menu1, menu2);
    public static List<Menu> menus2 = List.of(menu3, menu4, menu5);
    public static List<Menu> menus2Today = List.of(menu3, menu4);
    public static List<Menu> menus3 = List.of(menu6);
}
