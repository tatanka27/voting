package com.github.tatanka27.voting.service;

import com.github.tatanka27.voting.model.Menu;
import com.github.tatanka27.voting.repository.MenuRepository;
import com.github.tatanka27.voting.to.MenuTo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.tatanka27.voting.error.AppException;
import com.github.tatanka27.voting.model.Dish;
import com.github.tatanka27.voting.model.Restaurant;
import com.github.tatanka27.voting.repository.DishRepository;
import com.github.tatanka27.voting.repository.RestaurantRepository;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;
    private final DishRepository dishRepository;

    @Transactional
    public Menu addMenu(MenuTo menuTo, LocalDate dateMenu) {
        checkMenu(dateMenu, menuTo.getRestaurantId(), menuTo.getDishId());
        Restaurant restaurant = restaurantRepository.getExisted(menuTo.getRestaurantId());
        Dish dish = dishRepository.getExisted(menuTo.getDishId());

        return menuRepository.save(new Menu(null, dateMenu, restaurant, dish));
    }

    private void checkMenu(LocalDate dateMenu, int restaurantId, int dishId) {
        Menu menu = menuRepository.getMenu(restaurantId, dishId, dateMenu).orElse(null);

        if (menu != null) {
            throw new AppException(HttpStatus.BAD_REQUEST,
                    "Dish id=" + dishId + " has already added for restaurant=" + restaurantId);
        }
    }
}
