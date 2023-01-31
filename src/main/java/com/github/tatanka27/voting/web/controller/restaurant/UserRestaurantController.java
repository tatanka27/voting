package com.github.tatanka27.voting.web.controller.restaurant;

import com.github.tatanka27.voting.model.Restaurant;
import com.github.tatanka27.voting.to.RestaurantTo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = UserRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class UserRestaurantController extends RestaurantController {
    static final String REST_URL = "/api/restaurants";

    @Override
    @GetMapping
    @Cacheable("restaurants")
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @GetMapping("/{id}/with-menu")
    public ResponseEntity<RestaurantTo> getWithMenu(@PathVariable int id, @RequestParam LocalDate dateMenu) {
        return super.getWithMenu(id, dateMenu);
    }
}
