package ru.javaops.voting.web.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.voting.model.Item;
import ru.javaops.voting.service.ItemService;
import ru.javaops.voting.to.ItemTo;
import ru.javaops.voting.util.ItemUtil;

import java.net.URI;
import java.util.List;

import static ru.javaops.voting.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminItemController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AdminItemController extends ItemController {
    static final String REST_URL = "/api/admin/restaurants/{id}/items";

    ItemService itemService;

    @Override
    @GetMapping()
    public List<ItemTo> getAll(@PathVariable int id) {
        return super.getAll(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Item> create(@Valid @RequestBody ItemTo itemTo, @PathVariable int id) {
        log.info("create item {} for restaurant {}", itemTo, id);
        checkNew(itemTo);
        Item created = itemService.save(ItemUtil.createNewFromTo(itemTo), id);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
