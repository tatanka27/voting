package com.github.tatanka27.voting.web.controller;

import com.github.tatanka27.voting.model.Vote;
import com.github.tatanka27.voting.repository.VoteRepository;
import com.github.tatanka27.voting.service.VoteService;
import com.github.tatanka27.voting.to.VoteTo;
import com.github.tatanka27.voting.web.AuthUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.github.tatanka27.voting.util.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class VoteController {

    static final String REST_URL = "/api/profile/votes";
    private final VoteService voteService;
    private final VoteRepository voteRepository;
    private final Clock clock;

    @GetMapping
    public List<Vote> getAll(@AuthenticationPrincipal AuthUser authUser) {
        int userId = authUser.getUser().id();
        log.info("get votes for user {}", userId);
        return voteRepository.findAllByUserId(userId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vote> get(@AuthenticationPrincipal AuthUser authUser, @PathVariable int id) {
        log.info("get vote {}", id);
        int userId = authUser.getUser().id();
        return ResponseEntity.of(voteRepository.get(id, userId));
    }

    @GetMapping("/filter")
    public ResponseEntity<Vote> getByDateVote(@AuthenticationPrincipal AuthUser authUser, @RequestParam LocalDate dateVote) {
        log.info("get vote by dateVote {}", dateVote);
        int userId = authUser.getUser().id();
        return ResponseEntity.of(voteRepository.getByUserIdAndDateVote(userId, dateVote));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> create(@AuthenticationPrincipal AuthUser authUser, @Valid @RequestBody VoteTo voteTo) {
        int userId = authUser.getUser().id();
        log.info("vote user {} for restaurant {}", userId, voteTo.getRestaurantId());
        Vote created = voteService.save(userId, voteTo, LocalDateTime.now(clock));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@AuthenticationPrincipal AuthUser authUser, @PathVariable int id, @Valid @RequestBody VoteTo voteTo) {
        log.info("update vote {}", voteTo.getId());
        int userId = authUser.getUser().id();
        assureIdConsistent(voteTo, id);
        voteRepository.getExistedOrBelonged(id, userId);
        voteService.save(userId, voteTo, LocalDateTime.now(clock));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthUser authUser, @PathVariable int id) {
        log.info("delete vote {}", id);
        Vote vote = voteRepository.getExistedOrBelonged(id, authUser.id());
        voteRepository.delete(vote);
    }
}
