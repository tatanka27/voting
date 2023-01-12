package ru.javaops.voting.web.controller;

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
import ru.javaops.voting.model.User;
import ru.javaops.voting.repository.UserRepository;
import ru.javaops.voting.to.UserTo;
import ru.javaops.voting.util.UsersUtil;
import ru.javaops.voting.web.UniqueMailValidator;

import java.net.URI;

import static ru.javaops.voting.util.ValidationUtil.checkNew;

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