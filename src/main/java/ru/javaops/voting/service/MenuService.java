package ru.javaops.voting.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.voting.error.AppException;
import ru.javaops.voting.model.Dish;
import ru.javaops.voting.model.Menu;
import ru.javaops.voting.model.Restaurant;
import ru.javaops.voting.repository.DishRepository;
import ru.javaops.voting.repository.MenuRepository;
import ru.javaops.voting.repository.RestaurantRepository;
import ru.javaops.voting.to.MenuTo;

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
