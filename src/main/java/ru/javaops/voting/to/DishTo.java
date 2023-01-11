package ru.javaops.voting.to;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class DishTo extends NamedTo {
    @Column(name = "price", nullable = false)
    @NotNull
    Double price;
    public DishTo(Integer id, String name, Double price) {
        super(id, name);
        this.price = price;
    }
}
