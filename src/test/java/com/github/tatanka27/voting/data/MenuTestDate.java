package com.github.tatanka27.voting.data;

import com.github.tatanka27.voting.model.ItemMenu;
import com.github.tatanka27.voting.web.MatcherFactory;

import java.time.LocalDate;
import java.util.List;

public class MenuTestDate {

    public static final MatcherFactory.Matcher<ItemMenu> MENU_MATCHER =
            MatcherFactory.usingEqualsComparator(ItemMenu.class);

    public static ItemMenu menu1 = new ItemMenu(1, LocalDate.now(), DishTestData.dish1);
    public static ItemMenu menu2 = new ItemMenu(2, LocalDate.now(), DishTestData.dish2);
    public static ItemMenu menu3 = new ItemMenu(3, LocalDate.now(), DishTestData.dish3);
    public static ItemMenu menu4 = new ItemMenu(4, LocalDate.now(), DishTestData.dish4);
    public static ItemMenu menu5 = new ItemMenu(5, LocalDate.of(1900, 1, 1), DishTestData.dish5);
    public static ItemMenu menu6 = new ItemMenu(6, LocalDate.of(1900, 1, 1), DishTestData.dish6);

    public static List<ItemMenu> menus1 = List.of(menu1, menu2);
    public static List<ItemMenu> menus2 = List.of(menu3, menu4, menu5);
    public static List<ItemMenu> menus2Today = List.of(menu3, menu4);
    public static List<ItemMenu> menus3 = List.of(menu6);
}
