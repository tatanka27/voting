package com.github.tatanka27.voting.web.controller;

import com.github.tatanka27.voting.repository.UserRepository;
import com.github.tatanka27.voting.web.UniqueMailValidator;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.github.tatanka27.voting.model.User;
import com.github.tatanka27.voting.to.UserTo;
import com.github.tatanka27.voting.util.UsersUtil;

import java.net.URI;

import static com.github.tatanka27.voting.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = RegisterController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class RegisterController {
    static final String REST_URL = "/api/register";


    private UserRepository repository;
    private UniqueMailValidator emailValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(emailValidator);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> register(@Valid @RequestBody UserTo userTo) {
        log.info("register {}", userTo);
        checkNew(userTo);
        User created = prepareAndSave(UsersUtil.createNewFromTo(userTo));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL).build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    protected User prepareAndSave(User user) {
        return repository.save(UsersUtil.prepareToSave(user));
    }
}