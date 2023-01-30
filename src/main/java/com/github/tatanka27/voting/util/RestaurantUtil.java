package com.github.tatanka27.voting.util;

import com.github.tatanka27.voting.model.Menu;
import com.github.tatanka27.voting.to.RestaurantTo;
import lombok.experimental.UtilityClass;
import com.github.tatanka27.voting.model.Restaurant;

import java.util.List;

@UtilityClass
public class RestaurantUtil {
    public static RestaurantTo createTo(Restaurant restaurant, List<Menu> menu) {
        return new RestaurantTo(restaurant.getId(), restaurant.getName(), menu);
    }
}
