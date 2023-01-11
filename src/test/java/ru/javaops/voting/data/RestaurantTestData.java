package ru.javaops.voting.data;

import ru.javaops.voting.model.Restaurant;
import ru.javaops.voting.web.MatcherFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantTestData {

    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "dishes");

    public static MatcherFactory.Matcher<Restaurant> RESTAURANT_WITH_DISHES_MATCHER =
            MatcherFactory.usingAssertions(Restaurant.class,
                    (a, e) -> assertThat(a).usingRecursiveComparison()
                            .ignoringFields("dishes.restaurant").isEqualTo(e),
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
    public static Restaurant restaurant2WithMenuToday = new Restaurant(RESTAURANT2_ID, "Сытый гусь");
    public static Restaurant restaurant3 = new Restaurant(RESTAURANT3_ID, "Вкусно и точка");
    public static Restaurant restaurant4 = new Restaurant(RESTAURANT4_ID, "Теремок");

    public static List<Restaurant> restaurants = List.of(restaurant3, restaurant2, restaurant4, restaurant1);

    static {
        restaurant1.setDishes(DishTestData.menu1);
        restaurant2.setDishes(DishTestData.menu2);
        restaurant2WithMenuToday.setDishes(DishTestData.menu2Today);
        restaurant3.setDishes(DishTestData.menu3);
    }

    public static Restaurant getNew() {
        return new Restaurant(null, "New");
    }
}
