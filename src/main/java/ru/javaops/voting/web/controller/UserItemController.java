package ru.javaops.voting.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javaops.voting.to.ItemTo;

import java.util.List;

@RestController
@RequestMapping(value = UserItemController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserItemController extends ItemController {
    static final String REST_URL = "/api/restaurants/{id}/items";

    @GetMapping
    public List<ItemTo> getAll(@PathVariable int id) {
        return super.getAll(id);
    }
}
