package com.github.tatanka27.voting.web.controller;

import com.github.tatanka27.voting.model.Vote;
import com.github.tatanka27.voting.service.VoteService;
import com.github.tatanka27.voting.to.VoteTo;
import com.github.tatanka27.voting.web.AuthUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.Clock;
import java.time.LocalDateTime;

@RestController
@RequestMapping(value = VoteController.REST_URL,  produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class VoteController {

    static final String REST_URL = "/api/vote";

    private final VoteService voteService;
    private final Clock clock;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> addVote(@AuthenticationPrincipal AuthUser authUser, @Valid @RequestBody VoteTo voteTo) {
        int userId = authUser.getUser().id();
        log.info("vote user {} for restaurant {}", userId, voteTo.getRestaurantId());
        Vote created = voteService.create(userId, voteTo.getRestaurantId(), LocalDateTime.now(clock));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL).build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
