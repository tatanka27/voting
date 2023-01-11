package ru.javaops.voting.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.voting.model.Restaurant;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {

    @EntityGraph(attributePaths = {"dishes"}, type = EntityGraph.EntityGraphType.FETCH)
//    @Query("""
//                    SELECT DISTINCT d.restaurant FROM Dish d JOIN d.restaurant
//                    WHERE d.dateMenu=:dateMenu AND d.restaurant.id=:id
//            """)
    @Query("SELECT r FROM Restaurant r WHERE r.id=:id")
    Optional<Restaurant> getWithDish(int id);
}
