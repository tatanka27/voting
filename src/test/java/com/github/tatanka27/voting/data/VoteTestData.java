package com.github.tatanka27.voting.data;

import com.github.tatanka27.voting.model.Vote;
import com.github.tatanka27.voting.web.MatcherFactory;

import java.time.LocalDate;

import static com.github.tatanka27.voting.data.RestaurantTestData.restaurant1;
import static com.github.tatanka27.voting.data.UserTestData.admin;
import static org.assertj.core.api.Assertions.assertThat;

public class VoteTestData {
    public static MatcherFactory.Matcher<Vote> VOTE_MATCHER =
            MatcherFactory.usingAssertions(Vote.class,
                    (a, e) -> assertThat(a).usingRecursiveComparison()
                            .ignoringFields("user", "restaurant.name", "restaurant.dishes").isEqualTo(e),
                    (a, e) -> {
                        throw new UnsupportedOperationException();
                    });

    public static final int VOTE_ID = 1;

    public static final Vote vote = new Vote(VOTE_ID, LocalDate.now(), admin, restaurant1);

}
