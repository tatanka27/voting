package ru.javaops.voting.to;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class RestaurantTo extends NamedTo {
    int votes;
    public RestaurantTo(Integer id, String name, int votes) {
        super(id, name);
        this.votes = votes;
    }
}
