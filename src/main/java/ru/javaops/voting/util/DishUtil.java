package ru.javaops.voting.util;

import lombok.experimental.UtilityClass;
import ru.javaops.voting.model.Dish;
import ru.javaops.voting.to.DishTo;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class DishUtil {
    public static List<DishTo> getTos(Collection<Dish> items) {
        return items.stream()
                .map(DishUtil::createTo)
                .collect(Collectors.toList());
    }
    public static Dish createNewFromTo(DishTo itemTo) {
        return new Dish(null, itemTo.getName(), itemTo.getPrice(), LocalDate.now());
    }
    public static DishTo createTo(Dish item) {
        return new DishTo(item.getId(), item.getName(), item.getPrice());
    }
}
