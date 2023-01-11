package ru.javaops.voting.data;

import ru.javaops.voting.model.Restaurant;
import ru.javaops.voting.to.RestaurantTo;
import ru.javaops.voting.web.MatcherFactory;

import java.util.List;

import static ru.javaops.voting.data.ItemTestData.*;

public class RestaurantTestData {

    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "items");

    public static MatcherFactory.Matcher<RestaurantTo> RESTAURANT_TO_MATCHER = MatcherFactory.usingEqualsComparator(RestaurantTo.class);
    public static final int RESTAURANT_ID = 1;

    public static Restaurant restaurant1 = new Restaurant(RESTAURANT_ID, "Трезвая утка");
    public static Restaurant restaurant2 = new Restaurant(RESTAURANT_ID + 1, "Сытый гусь");
    public static Restaurant restaurant3 = new Restaurant(RESTAURANT_ID + 2, "Вкусно и точка");
    public static Restaurant restaurant4 = new Restaurant(RESTAURANT_ID + 3, "Теремок");

    public static List<Restaurant> restaurants = List.of(restaurant3, restaurant2, restaurant4, restaurant1);
    public static List<Restaurant> restaurantsForToday = List.of(restaurant2, restaurant1);

    static {
        restaurant1.setItems(menu1);
        restaurant2.setItems(menu2);
        restaurant3.setItems(menu3);
    }

    public static Restaurant getNew() {
        return new Restaurant(null, "New");
    }
}
