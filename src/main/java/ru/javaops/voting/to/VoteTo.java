package ru.javaops.voting.to;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VoteTo {
    @NotNull
    Integer restaurantId;
}
