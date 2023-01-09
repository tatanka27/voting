package ru.javaops.voting.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.voting.model.Restaurant;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static ru.javaops.voting.util.ValidationUtil.checkExisted;

@Repository
@Transactional(readOnly = true)
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Query("""
                    SELECT i.restaurant FROM Item i JOIN i.restaurant
                    WHERE i.dateMenu=:dateMenu
                    GROUP BY i.restaurant
                    having count(i) > 0
                    ORDER BY i.restaurant.name ASC
            """)
    List<Restaurant> findAllByDate(LocalDate dateMenu);

    default Restaurant getExisted(int id) {
        return checkExisted(findById(id).orElse(null), id);
    }

}
