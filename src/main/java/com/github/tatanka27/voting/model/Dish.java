package com.github.tatanka27.voting.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dish", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "price"}, name = "dish_unique_name_price_idx")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Dish extends NamedEntity {
    @Column(name = "price", nullable = false)
    @NotNull
    private Double price;

    public Dish(Integer id, String name, Double price) {
        super(id, name);
        this.price = price;
    }
}
