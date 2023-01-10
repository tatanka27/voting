package ru.javaops.voting.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.voting.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {
    @Query("""
                    SELECT i.restaurant FROM Item i JOIN i.restaurant
                    WHERE i.dateMenu=:dateMenu
                    GROUP BY i.restaurant
                    having count(i) > 0
                    ORDER BY i.restaurant.name ASC
            """)
    List<Restaurant> findAllByDate(LocalDate dateMenu);
}
