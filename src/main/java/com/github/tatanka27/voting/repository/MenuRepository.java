package com.github.tatanka27.voting.repository;

import com.github.tatanka27.voting.model.Menu;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface MenuRepository extends BaseRepository<Menu> {
    @EntityGraph(attributePaths = {"dish"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT m FROM Menu m WHERE m.restaurant.id=:restaurantId AND m.dateMenu=:dateMenu")
    List<Menu> getMenusByRestaurant_IdAndDateMenu(int restaurantId, LocalDate dateMenu);

    @Query("SELECT m FROM Menu m WHERE m.dateMenu=:dateMenu AND m.restaurant.id=:restaurantId AND m.dish.id=:dishId")
    Optional<Menu> getMenu(int restaurantId, int dishId, LocalDate dateMenu);
}
