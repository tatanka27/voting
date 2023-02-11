package com.github.tatanka27.voting.to;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.LocalDate;

@Value
@EqualsAndHashCode(callSuper = true)
public class ItemMenuTo extends BaseTo {
    @NotNull
    LocalDate dateMenu;

    @NotNull
    Integer dishId;

    public ItemMenuTo(Integer id, Integer dishId, LocalDate dateMenu) {
        super(id);
        this.dishId = dishId;
        this.dateMenu = dateMenu;
    }
}
