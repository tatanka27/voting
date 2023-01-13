package ru.javaops.voting.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.voting.model.Dish;

@Repository
@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository<Dish> {
}
