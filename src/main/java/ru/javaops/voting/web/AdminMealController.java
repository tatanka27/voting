package ru.javaops.voting.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.voting.model.Meal;
import ru.javaops.voting.repository.MealRepository;
import ru.javaops.voting.repository.RestaurantRepository;
import ru.javaops.voting.service.MealService;
import ru.javaops.voting.to.MealTo;
import ru.javaops.voting.util.MealsUtil;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static ru.javaops.voting.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminMealController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AdminMealController {
    static final String REST_URL = "/api/admin/restaurants/{id}/meals";

    MealRepository mealRepository;

    MealService mealService;

    @GetMapping()
    public List<MealTo> getAll(@PathVariable int id) {
        return MealsUtil.getTos(mealRepository.getByRestIdAndDateMeal(id, LocalDate.now()));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MealTo> create(@Valid @RequestBody Meal meal, @PathVariable int id) {
        checkNew(meal);
        MealTo mealCreated = MealsUtil.createTo(mealService.save(meal, id));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(mealCreated.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(mealCreated);
    }
}
