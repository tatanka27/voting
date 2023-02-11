package com.github.tatanka27.voting.web.controller;

import com.github.tatanka27.voting.model.Dish;
import com.github.tatanka27.voting.repository.DishRepository;
import com.github.tatanka27.voting.repository.RestaurantRepository;
import com.github.tatanka27.voting.service.DishService;
import com.github.tatanka27.voting.to.DishTo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.tatanka27.voting.util.ValidationUtil.assureIdConsistent;
import static com.github.tatanka27.voting.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminDishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class AdminDishController {

    static final String REST_URL = "/api/admin/restaurants/{restaurantId}/dishes";
    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;
    private final DishService dishService;

    @GetMapping
    public List<Dish> getAllByRestaurantId(@PathVariable int restaurantId) {
        log.info("get all dishes for restaurant {}", restaurantId);
        restaurantRepository.getExisted(restaurantId);
        return dishRepository.findAllByRestaurantId(restaurantId)
                .stream()
                .sorted(Comparator.comparing(Dish::getName))
                .collect(Collectors.toList());
    }

    @GetMapping("/{dishId}")
    public ResponseEntity<Dish> get(@PathVariable int restaurantId, @PathVariable int dishId) {
        log.info("get dish {} ", dishId);
        return ResponseEntity.of(dishRepository.get(dishId, restaurantId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> create(@Valid @RequestBody DishTo dishTo, @PathVariable int restaurantId) {
        log.info("create dish {} ", dishTo.getName());
        checkNew(dishTo);
        Dish created = dishService.save(dishTo, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(restaurantId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{dishId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable int dishId, @PathVariable int restaurantId, @Valid @RequestBody DishTo dishTo) {
        log.info("update dish {}", dishId);
        assureIdConsistent(dishTo, dishId);
        dishRepository.getExistedOrBelonged(dishId, restaurantId);
        dishService.save(dishTo, restaurantId);
    }

    @DeleteMapping("/{dishId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int dishId, @PathVariable int restaurantId) {
        log.info("delete vote {}", dishId);
        Dish dish = dishRepository.getExistedOrBelonged(dishId, restaurantId);
        dishRepository.delete(dish);
    }
}
