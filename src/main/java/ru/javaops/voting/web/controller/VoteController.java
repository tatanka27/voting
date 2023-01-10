package ru.javaops.voting.web.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
public class VoteController {

    static final String REST_URL = "/api/vote";
    VoteService voteService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void addVote(@AuthenticationPrincipal AuthUser authUser, @Valid @RequestBody VoteTo voteTo) {
        voteService.addVote(authUser.getUser().id(), voteTo.restaurantId(), LocalDateTime.now());
    }
}
