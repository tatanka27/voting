package ru.javaops.voting.data;

import ru.javaops.voting.model.Restaurant;
import ru.javaops.voting.to.RestaurantTo;
import ru.javaops.voting.web.MatcherFactory;

import java.util.List;

public class RestaurantTestData {

    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER =
            MatcherFactory.usingEqualsComparator(Restaurant.class);

    public static final MatcherFactory.Matcher<RestaurantTo> RESTAURANT_WITH_MENU_MATCHER =
            MatcherFactory.usingEqualsComparator(RestaurantTo.class);

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


    public static Restaurant getNew() {
        return new Restaurant(null, "New");
    }
}
