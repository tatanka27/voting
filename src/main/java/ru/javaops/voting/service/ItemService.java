package ru.javaops.voting.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.voting.model.Item;
import ru.javaops.voting.repository.ItemRepository;
import ru.javaops.voting.repository.RestaurantRepository;

@Service
@AllArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public Item save(Item item, int restaurantId) {
        item.setRestaurant(restaurantRepository.getExisted(restaurantId));
        return itemRepository.save(item);
    }
}