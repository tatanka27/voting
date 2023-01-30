package com.github.tatanka27.voting.service;

import com.github.tatanka27.voting.model.Vote;
import com.github.tatanka27.voting.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.tatanka27.voting.error.AppException;
import com.github.tatanka27.voting.model.Restaurant;
import com.github.tatanka27.voting.model.User;
import com.github.tatanka27.voting.repository.RestaurantRepository;
import com.github.tatanka27.voting.repository.UserRepository;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class VoteService {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final VoteRepository voteRepository;

    @Transactional
    public Vote addVote(int userId, int restaurantId, LocalDateTime dateTime) {
        Vote vote = checkAndGetVote(userId, dateTime);
        Restaurant restaurant = restaurantRepository.getExisted(restaurantId);
        User user = userRepository.getExisted(userId);

        if (vote != null) {
            vote.setRestaurant(restaurant);
        } else {
            vote = new Vote(null, dateTime.toLocalDate(), user, restaurant);
        }
        return voteRepository.save(vote);
    }

    private Vote checkAndGetVote(int userId, LocalDateTime dateTime) {
        Vote vote = voteRepository.findByUserIdAndDateVote(userId, dateTime.toLocalDate()).orElse(null);
        if (vote != null) {
            if (dateTime.getHour() > 11) {
                throw new AppException(HttpStatus.BAD_REQUEST, "You have already voted!");
            }
        }
        return vote;
    }
}
