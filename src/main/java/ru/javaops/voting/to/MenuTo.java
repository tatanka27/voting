package ru.javaops.voting.to;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class MenuTo extends BaseTo {
    @NotNull
    Integer restaurantId;

    @NotNull
    Integer dishId;

    public MenuTo(Integer id, Integer restaurantId, Integer dishId) {
        super(id);
        this.restaurantId = restaurantId;
        this.dishId = dishId;
    }
}
