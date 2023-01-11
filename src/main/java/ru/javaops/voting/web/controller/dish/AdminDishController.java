package ru.javaops.voting.web.controller.dish;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.voting.model.Dish;
import ru.javaops.voting.repository.DishRepository;
import ru.javaops.voting.service.DishService;
import ru.javaops.voting.to.DishTo;
import ru.javaops.voting.util.DishUtil;

import java.net.URI;
import java.util.List;

import static ru.javaops.voting.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminDishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AdminDishController extends DishController {
    static final String REST_URL = "/api/admin/restaurants/{restaurantId}/dishes";

    DishService dishService;
    DishRepository dishRepository;

    @Override
    @GetMapping
    public List<DishTo> getAll(@PathVariable int restaurantId) {
        return super.getAll(restaurantId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dish> get(@PathVariable int restaurantId, @PathVariable int id) {
        return ResponseEntity.of(dishRepository.findById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> create(@PathVariable int restaurantId, @Valid @RequestBody DishTo dishTo) {
        log.info("create dish {} for restaurant {}", dishTo, restaurantId);
        checkNew(dishTo);
        Dish created = dishService.save(DishUtil.createNewFromTo(dishTo), restaurantId);
        String url = REST_URL.replace("{restaurantId}", String.valueOf(restaurantId));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(url + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
