package ru.javaops.voting.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.voting.model.Dish;
import ru.javaops.voting.repository.DishRepository;
import ru.javaops.voting.repository.RestaurantRepository;

@Service
@AllArgsConstructor
public class DishService {
    private final DishRepository itemRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public Dish save(Dish item, int restaurantId) {
        item.setRestaurant(restaurantRepository.getExisted(restaurantId));
        return itemRepository.save(item);
    }
}