package ru.javaops.voting.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import ru.javaops.voting.repository.ItemRepository;
import ru.javaops.voting.to.ItemTo;
import ru.javaops.voting.util.ItemUtil;

import java.time.LocalDate;
import java.util.List;

public abstract class ItemController {
    @Autowired
    ItemRepository itemRepository;

    public List<ItemTo> getAll(@PathVariable int id) {
        return ItemUtil.getTos(itemRepository.getByRestIdAndDateMenu(id, LocalDate.now()));
    }
}
