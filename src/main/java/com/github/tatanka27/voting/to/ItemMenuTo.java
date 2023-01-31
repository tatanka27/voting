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
    int dishId;

    public ItemMenuTo(Integer id, int dishId, LocalDate dateMenu) {
        super(id);
        this.dishId = dishId;
        this.dateMenu = dateMenu;
    }
}
