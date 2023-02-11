package com.github.tatanka27.voting.repository;

import com.github.tatanka27.voting.error.DataConflictException;
import com.github.tatanka27.voting.model.Dish;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository<Dish> {
    @Query("SELECT d FROM Dish d WHERE d.restaurant.id = :restaurantId")
    List<Dish> findAllByRestaurantId(int restaurantId);

    @Query("SELECT d FROM Dish d WHERE d.id = :dishId and d.restaurant.id = :restaurantId")
    Optional<Dish> get(int dishId, int restaurantId);

    default Dish getExistedOrBelonged(int dishId, int restaurantId) {
        return get(dishId, restaurantId).orElseThrow(
                () -> new DataConflictException("Dish id=" + dishId + " is not exist or doesn't belong to Restaurant id=" + restaurantId));
    }
}
