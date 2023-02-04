package com.github.tatanka27.voting.service;

import com.github.tatanka27.voting.error.DataConflictException;
import com.github.tatanka27.voting.model.Dish;
import com.github.tatanka27.voting.model.ItemMenu;
import com.github.tatanka27.voting.model.Restaurant;
import com.github.tatanka27.voting.repository.DishRepository;
import com.github.tatanka27.voting.repository.ItemMenuRepository;
import com.github.tatanka27.voting.repository.RestaurantRepository;
import com.github.tatanka27.voting.to.ItemMenuTo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemMenuService {
    private final ItemMenuRepository itemMenuRepository;
    private final RestaurantRepository restaurantRepository;
    private final DishRepository dishRepository;

    @Transactional
    public ItemMenu create(ItemMenuTo itemMenuTo, int restaurantId) {
        Dish dish = dishRepository.getExisted(itemMenuTo.getDishId());
        Restaurant restaurant = restaurantRepository.getExisted(dish.getRestaurant().getId());

        if (restaurant.getId() != restaurantId) {
            throw new DataConflictException("Dish id=" + dish.getId() + " doesn't belong to the restaurant=" + restaurantId);
        }

        return itemMenuRepository.save(new ItemMenu(null, itemMenuTo.getDateMenu(), dish));
    }
}
