package ru.javaops.voting.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javaops.voting.model.Menu;
import ru.javaops.voting.model.Restaurant;
import ru.javaops.voting.repository.MenuRepository;
import ru.javaops.voting.repository.RestaurantRepository;
import ru.javaops.voting.to.RestaurantTo;
import ru.javaops.voting.util.RestaurantUtil;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantService {

    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;
    private final Clock clock;

    public RestaurantTo getWithMenu(int id) {
        Restaurant restaurant = restaurantRepository.getExisted(id);
        List<Menu> menu = menuRepository.getMenusByRestaurant_IdAndDateMenu(id, LocalDate.now(clock));

        return RestaurantUtil.createTo(restaurant, menu);
    }
}
