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
@Table(name = "meal", uniqueConstraints = {@UniqueConstraint(columnNames = {"rest_id", "name", "date_meal"}, name = "meal_unique_rest_name_date_idx")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Meal extends NamedEntity {
    @Column(name = "price", nullable = false)
    @NotNull
    private Double price;

    @Column(name = "date_meal", nullable = false)
    @NotNull
    private LocalDate dateMeal = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rest_id", nullable = false)
    @JsonIgnore
    private Restaurant restaurant;
}
