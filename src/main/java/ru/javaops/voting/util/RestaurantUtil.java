package ru.javaops.voting.util;

import lombok.experimental.UtilityClass;
import ru.javaops.voting.model.Menu;
import ru.javaops.voting.model.Restaurant;
import ru.javaops.voting.to.RestaurantTo;

import java.util.List;

@UtilityClass
public class RestaurantUtil {
    public static RestaurantTo createTo(Restaurant restaurant, List<Menu> menu) {
        return new RestaurantTo(restaurant.getId(), restaurant.getName(), menu);
    }
}
