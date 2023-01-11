package ru.javaops.voting.web.controller.dish;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javaops.voting.to.DishTo;

import java.util.List;

@RestController
@RequestMapping(value = UserDishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserDishController extends DishController {
    static final String REST_URL = "/api/restaurants/{id}/dishes";

    @Override
    @GetMapping
    public List<DishTo> getAll(@PathVariable int id) {
        return super.getAll(id);
    }
}
