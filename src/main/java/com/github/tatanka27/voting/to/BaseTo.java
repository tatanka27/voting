package com.github.tatanka27.voting.to;

import com.github.tatanka27.voting.HasId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseTo implements HasId {
    @Schema(hidden = true)
    protected Integer id;

    @Override
    public String toString() {
        return getClass().getSimpleName() + ":" + id;
    }
}
