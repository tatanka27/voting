package ru.javaops.voting.to;

import jakarta.validation.constraints.NotNull;

public record VoteTo(@NotNull Integer restaurantId) {
}
