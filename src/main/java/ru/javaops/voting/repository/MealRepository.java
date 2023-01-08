package ru.javaops.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.javaops.voting.model.Meal;

@Repository
public interface MealRepository extends JpaRepository<Meal, Integer> {
}
