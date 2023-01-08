package ru.javaops.voting.to;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.LocalDate;

@Value
@EqualsAndHashCode(callSuper = true)
public class MealTo extends NamedTo {
    @Column(name = "price", nullable = false)
    @NotNull
    Double price;
    public MealTo(Integer id, String name, Double price) {
        super(id, name);
        this.price = price;
    }
}
