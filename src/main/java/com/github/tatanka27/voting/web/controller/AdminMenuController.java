package com.github.tatanka27.voting.web.controller;

import com.github.tatanka27.voting.model.ItemMenu;
import com.github.tatanka27.voting.repository.ItemMenuRepository;
import com.github.tatanka27.voting.service.ItemMenuService;
import com.github.tatanka27.voting.to.ItemMenuTo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static com.github.tatanka27.voting.util.ValidationUtil.assureIdConsistent;
import static com.github.tatanka27.voting.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class AdminMenuController {

    static final String REST_URL = "/api/admin/restaurants/{restaurantId}/menu";
    private final ItemMenuRepository itemMenuRepository;
    private final ItemMenuService itemMenuService;

    @GetMapping("/{itemMenuId}")
    public ResponseEntity<ItemMenu> get(@PathVariable int itemMenuId, @PathVariable int restaurantId) {
        log.info("get itemMenu {} ", itemMenuId);
        return ResponseEntity.of(itemMenuRepository.get(itemMenuId, restaurantId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemMenu> create(@Valid @RequestBody ItemMenuTo itemMenuTo, @PathVariable int restaurantId) {
        log.info("add dish {} for restaurant {}, date {}", itemMenuTo.getDishId(), restaurantId, itemMenuTo.getDateMenu());
        checkNew(itemMenuTo);
        ItemMenu created = itemMenuService.save(itemMenuTo);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{itemMenuId}")
                .buildAndExpand(restaurantId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{itemMenuId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable int itemMenuId, @PathVariable int restaurantId, @Valid @RequestBody ItemMenuTo itemMenuTo) {
        log.info("update itemMenu {}", itemMenuId);
        assureIdConsistent(itemMenuTo, itemMenuId);
        itemMenuRepository.getExistedOrBelonged(itemMenuId, restaurantId);
        itemMenuService.save(itemMenuTo);
    }

    @DeleteMapping("/{itemMenuId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int itemMenuId, @PathVariable int restaurantId) {
        log.info("delete itemMenu {}", itemMenuId);
        ItemMenu itemMenu = itemMenuRepository.getExistedOrBelonged(itemMenuId, restaurantId);
        itemMenuRepository.delete(itemMenu);
    }
}
