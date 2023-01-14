package ru.javaops.voting.web.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javaops.voting.model.Menu;
import ru.javaops.voting.service.MenuService;
import ru.javaops.voting.to.MenuTo;

import java.time.Clock;
import java.time.LocalDate;

import static ru.javaops.voting.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AdminMenuController {

    static final String REST_URL = "/api/admin/menu";

    private MenuService menuService;

    private Clock clock;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> addMenu(@Valid @RequestBody MenuTo menuTo) {
        log.info("add dish {} for restaurant {}", menuTo.getDishId(), menuTo.getRestaurantId());
        checkNew(menuTo);
        Menu created = menuService.addMenu(menuTo, LocalDate.now(clock));
        return ResponseEntity.ok((created));
    }
}
