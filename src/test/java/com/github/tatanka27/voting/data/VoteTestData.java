package com.github.tatanka27.voting.data;

import com.github.tatanka27.voting.model.Vote;
import com.github.tatanka27.voting.web.MatcherFactory;

public class VoteTestData {
    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER =
            MatcherFactory.usingEqualsComparator(Vote.class);
}
