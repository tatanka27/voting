package com.github.tatanka27.voting.service;

import com.github.tatanka27.voting.model.Dish;
import com.github.tatanka27.voting.model.Restaurant;
import com.github.tatanka27.voting.repository.DishRepository;
import com.github.tatanka27.voting.repository.RestaurantRepository;
import com.github.tatanka27.voting.to.DishTo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DishService {

    private final RestaurantRepository restaurantRepository;

    private final DishRepository dishRepository;

    public Dish save(DishTo dishTo, int restaurantId) {
        Restaurant restaurant = restaurantRepository.getReferenceById(restaurantId);
        Dish dish = get(dishTo.getId());

        dish.setRestaurant(restaurant);
        dish.setName(dishTo.getName());
        dish.setPrice(dishTo.getPrice());

        return dishRepository.save(dish);
    }

    private Dish get(Integer id) {
        if (id != null) {
            return dishRepository.getExisted(id);
        } else {
            return new Dish();
        }
    }

}
