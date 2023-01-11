package ru.javaops.voting.data;

import ru.javaops.voting.model.Vote;

import java.time.LocalDate;
import java.util.List;

import static ru.javaops.voting.data.UserTestData.admin;
import static ru.javaops.voting.data.UserTestData.user;

public class VoteTestData {
    public static final int VOTE_ID = 1;

    public static Vote vote1 = new Vote(VOTE_ID, LocalDate.now(), user, RestaurantTestData.restaurant1);
    public static Vote vote2 = new Vote(VOTE_ID + 1 , LocalDate.now(), admin, RestaurantTestData.restaurant2);

    public static List<Vote> votes = List.of(vote1, vote2);
}
