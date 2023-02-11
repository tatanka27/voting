package com.github.tatanka27.voting.repository;

import com.github.tatanka27.voting.error.DataConflictException;
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
    List<ItemMenu> getMenuByRestaurantIdAndDateMenu(int restaurantId, LocalDate dateMenu);

    @EntityGraph(attributePaths = {"dish"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT i FROM ItemMenu i WHERE i.id = :itemMenuId and i.dish.restaurant.id = :restaurantId")
    Optional<ItemMenu> get(int itemMenuId, int restaurantId);

    default ItemMenu getExistedOrBelonged(int itemMenuId, int restaurantId) {
        return get(itemMenuId, restaurantId).orElseThrow(
                () -> new DataConflictException("ItemMenu id=" + itemMenuId + " is not exist or doesn't belong to Restaurant id=" + restaurantId));
    }
}
