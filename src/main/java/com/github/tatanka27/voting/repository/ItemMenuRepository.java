package com.github.tatanka27.voting.repository;

import com.github.tatanka27.voting.model.ItemMenu;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface ItemMenuRepository extends BaseRepository<ItemMenu> {
    @EntityGraph(attributePaths = {"dish"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT i FROM ItemMenu i WHERE i.dish.restaurant.id=:restaurantId AND i.dateMenu=:dateMenu")
    List<ItemMenu> getMenuByRestaurant_IdAndDateMenu(int restaurantId, LocalDate dateMenu);

    @Query("SELECT i FROM ItemMenu i WHERE i.dateMenu=:dateMenu AND i.dish.id=:dishId")
    Optional<ItemMenu> getItemMenu(int dishId, LocalDate dateMenu);
}
