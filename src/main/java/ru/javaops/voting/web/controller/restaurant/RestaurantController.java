package ru.javaops.voting.web.controller.restaurant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import ru.javaops.voting.model.Restaurant;
import ru.javaops.voting.repository.RestaurantRepository;
import ru.javaops.voting.service.RestaurantService;

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
        log.info("get restaurants by id");
        return ResponseEntity.of(restaurantRepository.findById(id));
    }

    public ResponseEntity<Restaurant> getWithDishes(@PathVariable int id) {
        log.info("get restaurants by id with dishes");
        Restaurant restaurant = restaurantService.getWithDishes(id, LocalDate.now());
        return ResponseEntity.ok(restaurant);
    }
}
