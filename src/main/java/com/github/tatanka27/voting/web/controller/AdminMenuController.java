package com.github.tatanka27.voting.web.controller;

import com.github.tatanka27.voting.model.ItemMenu;
import com.github.tatanka27.voting.service.ItemMenuService;
import com.github.tatanka27.voting.to.ItemMenuTo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static com.github.tatanka27.voting.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class AdminMenuController {

    static final String REST_URL = "/api/admin/restaurants/{restaurantId}/menu";

    private final ItemMenuService itemMenuService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemMenu> create(@Valid @RequestBody ItemMenuTo itemMenuTo, @PathVariable int restaurantId) {
        log.info("add dish {} for restaurant {}, date {}", itemMenuTo.getDishId(), restaurantId, itemMenuTo.getDateMenu());
        checkNew(itemMenuTo);
        ItemMenu created = itemMenuService.create(itemMenuTo, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{itemMenuId}")
                .buildAndExpand(restaurantId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
