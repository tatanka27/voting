package com.github.tatanka27.voting.service;

import com.github.tatanka27.voting.error.AppException;
import com.github.tatanka27.voting.model.Dish;
import com.github.tatanka27.voting.model.ItemMenu;
import com.github.tatanka27.voting.model.Restaurant;
import com.github.tatanka27.voting.repository.DishRepository;
import com.github.tatanka27.voting.repository.ItemMenuRepository;
import com.github.tatanka27.voting.repository.RestaurantRepository;
import com.github.tatanka27.voting.to.ItemMenuTo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ItemMenuService {
    private final ItemMenuRepository itemMenuRepository;
    private final RestaurantRepository restaurantRepository;
    private final DishRepository dishRepository;

    @Transactional
    public ItemMenu create(ItemMenuTo itemMenuTo, int restaurantId) {
        checkItemMenu(itemMenuTo.getDateMenu(), itemMenuTo.getDishId());
        Dish dish = dishRepository.getExisted(itemMenuTo.getDishId());
        Restaurant restaurant = restaurantRepository.getExisted(dish.getRestaurant().getId());

        if (restaurant.getId() != restaurantId) {
            throw new AppException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Dish id=" + dish.getId() + " doesn't belong to the restaurant=" + restaurantId);
        }

        return itemMenuRepository.save(new ItemMenu(null, itemMenuTo.getDateMenu(), dish));
    }

    private void checkItemMenu(LocalDate dateMenu, int dishId) {
        ItemMenu menu = itemMenuRepository.getItemMenu(dishId, dateMenu).orElse(null);

        if (menu != null) {
            throw new AppException(HttpStatus.BAD_REQUEST,
                    "Dish id=" + dishId + " has already added for date=" + dateMenu);
        }
    }
}
