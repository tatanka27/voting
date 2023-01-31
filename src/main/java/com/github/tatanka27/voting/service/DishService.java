package com.github.tatanka27.voting.service;

import com.github.tatanka27.voting.model.Dish;
import com.github.tatanka27.voting.model.Restaurant;
import com.github.tatanka27.voting.repository.DishRepository;
import com.github.tatanka27.voting.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DishService {

    private final RestaurantRepository restaurantRepository;

    private final DishRepository dishRepository;

    public Dish create(String name, double price, int restaurantId) {
        Restaurant restaurant = restaurantRepository.getExisted(restaurantId);
        return dishRepository.save(new Dish(null, name, price, restaurant));
    }
}
