package com.github.tatanka27.voting.error;

import lombok.Getter;
import org.springframework.lang.NonNull;

@Getter
public class AppException extends RuntimeException {

    public AppException(@NonNull String message) {
        super(message);
    }
}
