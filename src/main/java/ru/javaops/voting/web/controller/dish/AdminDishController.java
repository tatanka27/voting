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
    static final String REST_URL = "/api/admin/restaurants/{id}/items";

    DishService itemService;

    @Override
    @GetMapping()
    public List<DishTo> getAll(@PathVariable int id) {
        return super.getAll(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> create(@Valid @RequestBody DishTo itemTo, @PathVariable int id) {
        log.info("create item {} for restaurant {}", itemTo, id);
        checkNew(itemTo);
        Dish created = itemService.save(DishUtil.createNewFromTo(itemTo), id);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
