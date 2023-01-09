package ru.javaops.voting.util;

import lombok.experimental.UtilityClass;
import ru.javaops.voting.model.Restaurant;
import ru.javaops.voting.model.Vote;
import ru.javaops.voting.to.RestaurantTo;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@UtilityClass
public class RestaurantUtil {
    public static List<RestaurantTo> getTos(Collection<Restaurant> restaurants, Collection<Vote> votes) {
        Map<Restaurant, Long> votesByRest = votes.stream()
                .collect(
                        Collectors.groupingBy(Vote::getRestaurant, Collectors.counting())
                );
        return restaurants.stream()
                .map(restaurant -> createTo(restaurant, votesByRest.getOrDefault(restaurant, 0L).intValue()))
                .sorted(Comparator.comparing(RestaurantTo::getVotes))
                .collect(Collectors.toList());
    }

    public static RestaurantTo createTo(Restaurant restaurant, int votes) {
        return new RestaurantTo(restaurant.getId(), restaurant.getName(), votes);
    }
}
