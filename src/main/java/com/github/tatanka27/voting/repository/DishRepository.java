package com.github.tatanka27.voting.repository;

import com.github.tatanka27.voting.model.Dish;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository<Dish> {
    List<Dish> findAllByRestaurantId(int restaurantId);
}
