package com.github.tatanka27.voting.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "item_menu", uniqueConstraints = {@UniqueConstraint(columnNames = {"date_menu", "dish_id"}, name = "menu_unique_date_dish_idx")})
@Getter
@Setter
@NoArgsConstructor
public class ItemMenu extends BaseEntity {

    @Column(name = "date_menu", nullable = false)
    @NotNull
    private LocalDate dateMenu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Dish dish;

    public ItemMenu(Integer id, LocalDate dateMenu, Dish dish) {
        super(id);
        this.dateMenu = dateMenu;
        this.dish = dish;
    }

}
