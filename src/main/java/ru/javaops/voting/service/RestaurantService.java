package ru.javaops.voting.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javaops.voting.model.Item;
import ru.javaops.voting.model.Restaurant;
import ru.javaops.voting.repository.ItemRepository;
import ru.javaops.voting.repository.RestaurantRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RestaurantService {
    private final ItemRepository itemRepository;
    private final RestaurantRepository restaurantRepository;

    public Optional<Restaurant> getWithItems(int id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
        if (restaurant != null) {
            List<Item> items = itemRepository.getByRestIdAndDateMenu(id, LocalDate.now());
            restaurant.setItems(items);
            return Optional.of(restaurant);
        }
        return Optional.empty();
    }
}

