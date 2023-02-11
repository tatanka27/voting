package com.github.tatanka27.voting.service;

import com.github.tatanka27.voting.error.IllegalRequestDataException;
import com.github.tatanka27.voting.model.Restaurant;
import com.github.tatanka27.voting.model.User;
import com.github.tatanka27.voting.model.Vote;
import com.github.tatanka27.voting.repository.RestaurantRepository;
import com.github.tatanka27.voting.repository.UserRepository;
import com.github.tatanka27.voting.repository.VoteRepository;
import com.github.tatanka27.voting.to.VoteTo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class VoteService {

    private static final int CAN_REVOTE_BEFORE_HOUR = 11;
    private static final int CAN_REVOTE_BEFORE_MINUTE = 0;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final VoteRepository voteRepository;

    @Transactional
    public Vote save(int userId, VoteTo voteTo, LocalDateTime dateTime) {
        Vote vote = checkAndGetVote(userId, dateTime);
        Restaurant restaurant = restaurantRepository.getReferenceById(voteTo.getRestaurantId());
        User user = userRepository.getReferenceById(userId);

        vote.setDateVote(dateTime.toLocalDate());
        vote.setRestaurant(restaurant);
        vote.setUser(user);

        return voteRepository.save(vote);
    }

    private Vote checkAndGetVote(int userId, LocalDateTime dateTime) {
        Vote vote = voteRepository.getByUserIdAndDateVote(userId, dateTime.toLocalDate()).orElse(new Vote());
        if (vote.getId() != null) {
            if (dateTime.toLocalTime().isAfter(LocalTime.of(CAN_REVOTE_BEFORE_HOUR, CAN_REVOTE_BEFORE_MINUTE))) {
                throw new IllegalRequestDataException("You can't change your vote after 11 a.m.");
            }
        }
        return vote;
    }
}
