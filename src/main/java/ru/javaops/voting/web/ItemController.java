package ru.javaops.voting.web;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javaops.voting.repository.ItemRepository;
import ru.javaops.voting.to.ItemTo;
import ru.javaops.voting.util.ItemUtil;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = ItemController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ItemController {
    static final String REST_URL = "/api/restaurants/{id}/items";

    ItemRepository itemRepository;

    @GetMapping
    public List<ItemTo> getAll(@PathVariable int id) {
        return ItemUtil.getTos(itemRepository.getByRestIdAndDateMenu(id, LocalDate.now()));
    }
}
