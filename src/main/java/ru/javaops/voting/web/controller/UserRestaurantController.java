package ru.javaops.voting.web.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javaops.voting.model.Restaurant;
import ru.javaops.voting.model.Vote;
import ru.javaops.voting.repository.RestaurantRepository;
import ru.javaops.voting.repository.VoteRepository;
import ru.javaops.voting.to.RestaurantTo;
import ru.javaops.voting.util.RestaurantUtil;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = UserRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserRestaurantController {
    static final String REST_URL = "/api/restaurants";

    RestaurantRepository restaurantRepository;

    VoteRepository voteRepository;

    @GetMapping
    public List<RestaurantTo> getAllWithVotes() {
        log.info("get all restaurant with votes");
        List<Restaurant> restaurants = restaurantRepository.findAllByDate(LocalDate.now());
        List<Vote> votes = voteRepository.findAll();
        return RestaurantUtil.getTos(restaurants, votes);
    }
}
