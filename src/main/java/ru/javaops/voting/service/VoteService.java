package ru.javaops.voting.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.voting.error.AppException;
import ru.javaops.voting.model.Restaurant;
import ru.javaops.voting.model.User;
import ru.javaops.voting.model.Vote;
import ru.javaops.voting.repository.RestaurantRepository;
import ru.javaops.voting.repository.UserRepository;
import ru.javaops.voting.repository.VoteRepository;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class VoteService {

    RestaurantRepository restaurantRepository;
    UserRepository userRepository;
    VoteRepository voteRepository;

    @Transactional
    public void addVote(int userId, int restaurantId, LocalDateTime dateTime) {
        Restaurant restaurant = restaurantRepository.getExisted(restaurantId);
        User user = userRepository.getExisted(userId);
        Vote vote = checkAndGetVote(userId, dateTime);

        if (vote != null) {
            vote.setRestaurant(restaurant);
        } else {
            vote = new Vote(null, dateTime.toLocalDate(), user, restaurant);
        }
        voteRepository.save(vote);
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
