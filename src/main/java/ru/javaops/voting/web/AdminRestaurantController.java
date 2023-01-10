package ru.javaops.voting.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.voting.model.Restaurant;
import ru.javaops.voting.repository.RestaurantRepository;
import ru.javaops.voting.service.RestaurantService;

import java.net.URI;
import java.util.List;

import static ru.javaops.voting.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AdminRestaurantController {
    static final String REST_URL = "/api/admin/restaurants";

    RestaurantRepository restaurantRepository;

    RestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    @GetMapping("/{id}/with-items")
    public ResponseEntity<Restaurant> getWithItems(@PathVariable int id) {
        return ResponseEntity.of(restaurantService.getWithItems(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> create(@Valid @RequestBody Restaurant restaurant) {
        checkNew(restaurant);
        Restaurant restCreated = restaurantRepository.save(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(restCreated.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(restCreated);
    }
}
