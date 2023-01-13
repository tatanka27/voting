package ru.javaops.voting.data;

import ru.javaops.voting.model.Menu;
import ru.javaops.voting.web.MatcherFactory;

import java.time.LocalDate;
import java.util.List;

import static ru.javaops.voting.data.DishTestData.*;
import static ru.javaops.voting.data.RestaurantTestData.*;

public class MenuTestDate {

    public static final MatcherFactory.Matcher<Menu> MENU_MATCHER =
            MatcherFactory.usingEqualsComparator(Menu.class);

    public static Menu menu1 = new Menu(1, LocalDate.now(), restaurant1, dish1);
    public static Menu menu2 = new Menu(2, LocalDate.now(), restaurant1, dish2);
    public static Menu menu3 = new Menu(3, LocalDate.now(), restaurant2, dish3);
    public static Menu menu4 = new Menu(4, LocalDate.now(), restaurant2, dish4);
    public static Menu menu5 = new Menu(5, LocalDate.of(1900, 1, 1), restaurant2, dish5);
    public static Menu menu6 = new Menu(6, LocalDate.of(1900, 1, 1), restaurant3, dish6);

    public static List<Menu> menus1 = List.of(menu1, menu2);
    public static List<Menu> menus2 = List.of(menu3, menu4, menu5);
    public static List<Menu> menus2Today = List.of(menu3, menu4);
    public static List<Menu> menus3 = List.of(menu6);
}
