package com.github.tatanka27.voting.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.github.tatanka27.voting.model.Menu;
import com.github.tatanka27.voting.model.Restaurant;
import com.github.tatanka27.voting.repository.MenuRepository;
import com.github.tatanka27.voting.repository.RestaurantRepository;
import com.github.tatanka27.voting.to.RestaurantTo;
import com.github.tatanka27.voting.util.RestaurantUtil;

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
