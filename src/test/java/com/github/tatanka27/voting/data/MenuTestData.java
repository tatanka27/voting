package com.github.tatanka27.voting.data;

import com.github.tatanka27.voting.model.Dish;
import com.github.tatanka27.voting.model.ItemMenu;
import com.github.tatanka27.voting.web.MatcherFactory;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MenuTestData {

    public static MatcherFactory.Matcher<ItemMenu> MENU_MATCHER =
            MatcherFactory.usingAssertions(ItemMenu.class,
                    (a, e) -> assertThat(a).usingRecursiveComparison()
                            .ignoringFields("dish.restaurant").isEqualTo(e),
                    (a, e) -> {
                        throw new UnsupportedOperationException();
                    });

    public static ItemMenu itemMenu1 = new ItemMenu(1, LocalDate.now(), DishTestData.dish1);
    public static ItemMenu itemMenu2 = new ItemMenu(2, LocalDate.now(), DishTestData.dish3);
    public static ItemMenu itemMenu3 = new ItemMenu(3, LocalDate.now(), DishTestData.dish4);
    public static ItemMenu itemMenu4 = new ItemMenu(4, LocalDate.of(1900, 1, 1), DishTestData.dish5);
    public static ItemMenu itemMenu5 = new ItemMenu(5, LocalDate.of(1900, 1, 1), DishTestData.dish6);

    public static List<ItemMenu> menu2Today = List.of(itemMenu2, itemMenu3);
}
