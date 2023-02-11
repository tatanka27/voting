package com.github.tatanka27.voting.to;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class DishTo extends NamedTo {

    @NotNull
    Double price;

    public DishTo(Integer id, String name, Double price) {
        super(id, name);
        this.price = price;
    }
}
