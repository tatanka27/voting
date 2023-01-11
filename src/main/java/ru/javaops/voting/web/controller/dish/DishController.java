package ru.javaops.voting.web.controller.dish;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import ru.javaops.voting.repository.DishRepository;
import ru.javaops.voting.to.DishTo;
import ru.javaops.voting.util.DishUtil;

import java.time.LocalDate;
import java.util.List;

@Slf4j
public abstract class DishController {
    @Autowired
    DishRepository dishRepository;

    public List<DishTo> getAll(@PathVariable int id) {
        log.info("get all items for restaurant {}", id);
        return DishUtil.getTos(dishRepository.getByRestIdAndDateMenu(id, LocalDate.now()));
    }
}
