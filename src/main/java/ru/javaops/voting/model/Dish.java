package ru.javaops.voting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "dish", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "name", "date_menu"}, name = "dish")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Dish extends NamedEntity {
    @Column(name = "price", nullable = false)
    @NotNull
    private Double price;

    @Column(name = "date_menu", nullable = false)
    @NotNull
    private LocalDate dateMenu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonIgnore
    private Restaurant restaurant;

    public Dish(Integer id, String name, Double price) {
        super(id, name);
        this.price = price;
        this.dateMenu = LocalDate.now();
    }

    public Dish(Integer id, String name, Double price, LocalDate dateMenu) {
        super(id, name);
        this.price = price;
        this.dateMenu = dateMenu;
    }
}
