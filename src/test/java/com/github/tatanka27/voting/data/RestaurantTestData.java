package com.github.tatanka27.voting.data;

import com.github.tatanka27.voting.model.Restaurant;
import com.github.tatanka27.voting.to.RestaurantTo;
import com.github.tatanka27.voting.web.MatcherFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "dishes");

    public static MatcherFactory.Matcher<RestaurantTo> RESTAURANT_WITH_MENU_MATCHER =
            MatcherFactory.usingAssertions(RestaurantTo.class,
                    (a, e) -> assertThat(a).usingRecursiveComparison()
                            .ignoringFields("menu.dish.restaurant", "menu.dateMenu").isEqualTo(e),
                    (a, e) -> {
                        throw new UnsupportedOperationException();
                    });

    public static final int RESTAURANT1_ID = 1;
    public static final int RESTAURANT2_ID = 2;
    public static final int RESTAURANT3_ID = 3;
    public static final int RESTAURANT4_ID = 4;
    public static final int RESTAURANT_NOT_FOUND_ID = 100;

    public static Restaurant restaurant1 = new Restaurant(RESTAURANT1_ID, "Трезвая утка");
    public static Restaurant restaurant2 = new Restaurant(RESTAURANT2_ID, "Сытый гусь");
    public static Restaurant restaurant3 = new Restaurant(RESTAURANT3_ID, "Вкусно и точка");
    public static Restaurant restaurant4 = new Restaurant(RESTAURANT4_ID, "Теремок");

    public static List<Restaurant> restaurants = List.of(restaurant3, restaurant2, restaurant4, restaurant1);


    public static Restaurant getNew() {
        return new Restaurant(null, "Новый ресторан");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT1_ID, "Обновленный ресторан");
    }
}
