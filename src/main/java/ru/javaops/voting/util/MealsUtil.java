package ru.javaops.voting.util;

import lombok.experimental.UtilityClass;
import ru.javaops.voting.model.Meal;
import ru.javaops.voting.to.MealTo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class MealsUtil {
    public static List<MealTo> getTos(Collection<Meal> meals) {
        return meals.stream()
                .map(MealsUtil::createTo)
                .collect(Collectors.toList());
    }

    public static MealTo createTo(Meal meal) {
        return new MealTo(meal.getId(), meal.getName(), meal.getPrice());
    }
}
