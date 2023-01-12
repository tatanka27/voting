package ru.javaops.voting.data;

import ru.javaops.voting.model.Vote;
import ru.javaops.voting.web.MatcherFactory;

public class VoteTestData {
    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER =
            MatcherFactory.usingEqualsComparator(Vote.class);
}
