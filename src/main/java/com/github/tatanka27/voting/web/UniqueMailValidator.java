package com.github.tatanka27.voting.web;

import com.github.tatanka27.voting.HasIdAndEmail;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import com.github.tatanka27.voting.repository.UserRepository;

@Component
@AllArgsConstructor
public class UniqueMailValidator implements org.springframework.validation.Validator {
    public static final String EXCEPTION_DUPLICATE_EMAIL = "User with this email already exists";

    private final UserRepository repository;

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return HasIdAndEmail.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        HasIdAndEmail user = ((HasIdAndEmail) target);
        if (StringUtils.hasText(user.getEmail())) {
            if (repository.findByEmailIgnoreCase(user.getEmail()).isPresent()) {
                errors.rejectValue("email", "", EXCEPTION_DUPLICATE_EMAIL);
            }
        }
    }
}
