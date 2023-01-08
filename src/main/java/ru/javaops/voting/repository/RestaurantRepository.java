package ru.javaops.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.voting.model.Restaurant;

import java.util.List;
import java.util.Optional;

import static ru.javaops.voting.util.ValidationUtil.checkExisted;

@Repository
@Transactional(readOnly = true)
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    // TODO
    @Query("SELECT r FROM Restaurant r join Meal m on r.id=m.restaurant.id GROUP BY r.name having count(r.meals) > 0 ORDER BY r.name ASC")
    List<Restaurant> findAllWithMeals();

    default Restaurant getExisted(int id) {
        return checkExisted(findById(id).orElse(null), id);
    }
}
