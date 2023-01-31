package com.github.tatanka27.voting.util;

import com.github.tatanka27.voting.model.ItemMenu;
import com.github.tatanka27.voting.model.Restaurant;
import com.github.tatanka27.voting.to.RestaurantTo;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class RestaurantUtil {
    public static RestaurantTo createTo(Restaurant restaurant, List<ItemMenu> menu) {
        return new RestaurantTo(restaurant.getId(), restaurant.getName(), menu);
    }
}
