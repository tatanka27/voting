package ru.javaops.voting.web.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.voting.model.Dish;
import ru.javaops.voting.repository.DishRepository;

import java.net.URI;
import java.util.List;

import static ru.javaops.voting.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminDishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AdminDishController {
    static final String REST_URL = "/api/admin/dishes";

    private DishRepository dishRepository;

    @GetMapping
    @Cacheable("dishes")
    public List<Dish> getAll() {
        log.info("get all dishes");
        return dishRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dish> get(@PathVariable int id) {
        log.info("get dish {} ", id);
        return ResponseEntity.of(dishRepository.findById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @CacheEvict(value = "dishes", allEntries = true)
    public ResponseEntity<Dish> create(@Valid @RequestBody Dish dish) {
        log.info("create dish {} ", dish.getName());
        checkNew(dish);
        Dish created = dishRepository.save(dish);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
