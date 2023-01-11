package ru.javaops.voting.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javaops.voting.model.Dish;
import ru.javaops.voting.model.Restaurant;
import ru.javaops.voting.repository.DishRepository;
import ru.javaops.voting.repository.RestaurantRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantService {

    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;

    public Restaurant getWithDishes(int id, LocalDate dateMenu) {
        Restaurant restaurant = restaurantRepository.getExisted(id);
        List<Dish> dishes = dishRepository.getByRestIdAndDateMenu(id, dateMenu);
        restaurant.setDishes(dishes);
        return restaurant;
    }
}
