package ru.javaops.voting.web.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.javaops.voting.service.VoteService;
import ru.javaops.voting.to.VoteTo;
import ru.javaops.voting.web.AuthUser;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = VoteController.REST_URL)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class VoteController {

    static final String REST_URL = "/api/vote";
    VoteService voteService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void addVote(@AuthenticationPrincipal AuthUser authUser, @Valid @RequestBody VoteTo voteTo) {
        int userId = authUser.getUser().id();
        log.info("vote user {} for restaurant {}", userId, voteTo.restaurantId());
        voteService.addVote(userId, voteTo.restaurantId(), LocalDateTime.now());
    }
}
