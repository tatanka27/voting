package com.github.tatanka27.voting.web.controller.restaurant;

import com.github.tatanka27.voting.model.Restaurant;
import com.github.tatanka27.voting.repository.RestaurantRepository;
import com.github.tatanka27.voting.service.RestaurantService;
import com.github.tatanka27.voting.to.RestaurantTo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Slf4j
public abstract class RestaurantController {

    @Autowired
    protected RestaurantRepository restaurantRepository;

    @Autowired
    protected RestaurantService restaurantService;

    public List<Restaurant> getAll() {
        log.info("get all restaurants");
        return restaurantRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public ResponseEntity<Restaurant> get(@PathVariable int id) {
        log.info("get restaurant {}", id);
        return ResponseEntity.of(restaurantRepository.findById(id));
    }

    public RestaurantTo getWithMenu(@PathVariable int id, @RequestParam LocalDate dateMenu) {
        log.info("get restaurant {} with menu", id);
        RestaurantTo restaurantTo = restaurantService.getWithMenu(id, dateMenu);
        return restaurantTo;
    }
}
