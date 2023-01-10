package ru.javaops.voting.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import ru.javaops.voting.repository.ItemRepository;
import ru.javaops.voting.to.ItemTo;
import ru.javaops.voting.util.ItemUtil;

import java.time.LocalDate;
import java.util.List;

@Slf4j
public abstract class ItemController {
    @Autowired
    ItemRepository itemRepository;

    public List<ItemTo> getAll(@PathVariable int id) {
        log.info("get all items for restaurant {}", id);
        return ItemUtil.getTos(itemRepository.getByRestIdAndDateMenu(id, LocalDate.now()));
    }
}
