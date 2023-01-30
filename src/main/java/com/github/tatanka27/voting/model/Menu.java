package com.github.tatanka27.voting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "menu", uniqueConstraints = {@UniqueConstraint(columnNames = {"date_menu", "restaurant_id", "dish_id"}, name = "menu_unique_date_rest_dish_idx")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu extends BaseEntity {

    @Column(name = "date_menu", nullable = false)
    @NotNull
    @JsonIgnore
    private LocalDate dateMenu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonIgnore
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id", nullable = false)
    private Dish dish;

    public Menu(Integer id, LocalDate dateMenu, Restaurant restaurant, Dish dish) {
        super(id);
        this.dateMenu = dateMenu;
        this.restaurant = restaurant;
        this.dish = dish;
    }

}
