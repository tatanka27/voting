package ru.javaops.voting.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.voting.model.Item;
import ru.javaops.voting.repository.ItemRepository;
import ru.javaops.voting.service.ItemService;
import ru.javaops.voting.to.ItemTo;
import ru.javaops.voting.util.ItemUtil;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static ru.javaops.voting.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminItemController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AdminItemController {
    static final String REST_URL = "/api/admin/restaurants/{id}/items";

    ItemRepository itemRepository;

    ItemService itemService;

    @GetMapping()
    public List<ItemTo> getAll(@PathVariable int id) {
        return ItemUtil.getTos(itemRepository.getByRestIdAndDateMenu(id, LocalDate.now()));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemTo> create(@Valid @RequestBody Item item, @PathVariable int id) {
        checkNew(item);
        ItemTo created = ItemUtil.createTo(itemService.save(item, id));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
