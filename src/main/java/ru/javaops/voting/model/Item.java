package ru.javaops.voting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "item", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "name", "date_menu"}, name = "item_unique_rest_name_date_idx")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends NamedEntity {
    @Column(name = "price", nullable = false)
    @NotNull
    private Double price;

    @Column(name = "date_menu", nullable = false)
    @NotNull
    private LocalDate dateMenu = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonIgnore
    private Restaurant restaurant;

    public Item(Integer id, String name, Double price, Restaurant restaurant) {
        super(id, name);
        this.price = price;
        this.dateMenu = LocalDate.now();
        this.restaurant = restaurant;
    }
}
