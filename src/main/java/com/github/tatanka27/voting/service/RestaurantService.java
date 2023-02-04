package com.github.tatanka27.voting.service;

import com.github.tatanka27.voting.model.ItemMenu;
import com.github.tatanka27.voting.model.Restaurant;
import com.github.tatanka27.voting.repository.ItemMenuRepository;
import com.github.tatanka27.voting.repository.RestaurantRepository;
import com.github.tatanka27.voting.to.RestaurantTo;
import com.github.tatanka27.voting.util.RestaurantUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final ItemMenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public RestaurantTo getWithMenu(int restaurantsId, LocalDate dateMenu) {
        Assert.notNull(dateMenu, "dateMenu must not be null");
        Restaurant restaurant = restaurantRepository.getExisted(restaurantsId);
        List<ItemMenu> menu = menuRepository.getMenuByRestaurant_IdAndDateMenu(restaurantsId, dateMenu);

        return RestaurantUtil.createTo(restaurant, menu);
    }
}