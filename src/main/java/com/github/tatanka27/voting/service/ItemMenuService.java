package com.github.tatanka27.voting.service;

import com.github.tatanka27.voting.model.Dish;
import com.github.tatanka27.voting.model.ItemMenu;
import com.github.tatanka27.voting.repository.DishRepository;
import com.github.tatanka27.voting.repository.ItemMenuRepository;
import com.github.tatanka27.voting.to.ItemMenuTo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemMenuService {
    private final ItemMenuRepository itemMenuRepository;
    private final DishRepository dishRepository;

    @Transactional
    public ItemMenu save(ItemMenuTo itemMenuTo) {
        Dish dish = dishRepository.getExisted(itemMenuTo.getDishId());
        ItemMenu itemMenu = get(itemMenuTo.getId());

        itemMenu.setDish(dish);
        itemMenu.setDateMenu(itemMenuTo.getDateMenu());

        return itemMenuRepository.save(itemMenu);
    }

    private ItemMenu get(Integer id) {
        if (id != null) {
            return itemMenuRepository.getExisted(id);
        } else {
            return new ItemMenu();
        }
    }
}
