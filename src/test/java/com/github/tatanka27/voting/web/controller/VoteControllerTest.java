package com.github.tatanka27.voting.web.controller;

import com.github.tatanka27.voting.model.Vote;
import com.github.tatanka27.voting.repository.VoteRepository;
import com.github.tatanka27.voting.to.VoteTo;
import com.github.tatanka27.voting.util.JsonUtil;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.*;

import static com.github.tatanka27.voting.data.RestaurantTestData.*;
import static com.github.tatanka27.voting.data.UserTestData.USER_MAIL;
import static com.github.tatanka27.voting.data.UserTestData.user;
import static com.github.tatanka27.voting.data.VoteTestData.VOTE_MATCHER;
import static com.github.tatanka27.voting.web.controller.VoteController.REST_URL;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VoteControllerTest {

    protected static class VoteBefore11Test extends AbstractControllerTest {

        @Autowired
        VoteRepository voteRepository;

        @Qualifier("clockBefore11")
        @Autowired
        Clock clock;

        @Test
        void getUnauthorized() throws Exception {
            perform(MockMvcRequestBuilders.get(REST_URL))
                    .andExpect(status().isUnauthorized());
        }

        @Test
        @WithUserDetails(value = USER_MAIL)
        @Disabled
        void addFirstVote() throws Exception {
            VoteTo newTo = new VoteTo(null, RESTAURANT1_ID);
            Vote newVote = new Vote(null, LocalDate.now(clock), user, restaurant1);

            ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtil.writeValue(newTo)));

            Vote created = VOTE_MATCHER.readFromJson(action);
            int newId = created.id();
            newVote.setId(newId);
            VOTE_MATCHER.assertMatch(created, newVote);
            VOTE_MATCHER.assertMatch(voteRepository.getExisted(newId), newVote);
        }

        @Test
        @WithUserDetails(value = USER_MAIL)
        @Disabled
        void addVSecondVoteBefore11() throws Exception {
            VoteTo newTo1 = new VoteTo(null, RESTAURANT1_ID);
            VoteTo newTo2 = new VoteTo(null, RESTAURANT2_ID);
            Vote newVote = new Vote(null, LocalDate.now(clock), user, restaurant2);

            perform(MockMvcRequestBuilders.post(REST_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtil.writeValue(newTo1)))
                    .andExpect(status().isCreated());

            ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtil.writeValue(newTo2)))
                    .andExpect(status().isCreated());

            Vote created = VOTE_MATCHER.readFromJson(action);
            int newId = created.id();
            newVote.setId(newId);
            VOTE_MATCHER.assertMatch(created, newVote);
            VOTE_MATCHER.assertMatch(voteRepository.getExisted(newId), newVote);
        }

        @TestConfiguration
        static class TesClockConfig {

            @Bean("clockBefore11")
            @Primary
            public Clock fixedClock() {
                LocalTime time = LocalTime.parse("10:00");
                LocalDate date = LocalDate.parse("2022-01-01");
                LocalDateTime dateTime = LocalDateTime.of(date, time);
                return Clock.fixed(dateTime.toInstant(ZoneOffset.UTC), ZoneOffset.UTC);
            }
        }
    }

    protected static class VoteAfter11Test extends AbstractControllerTest {
        @Test
        @WithUserDetails(value = USER_MAIL)
        @Disabled
        void addSecondVoteAfter11() throws Exception {
            VoteTo newTo1 = new VoteTo(null, RESTAURANT1_ID);
            VoteTo newTo2 = new VoteTo(null, RESTAURANT2_ID);

            perform(MockMvcRequestBuilders.post(REST_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtil.writeValue(newTo1)))
                    .andExpect(status().isCreated());

            perform(MockMvcRequestBuilders.post(REST_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtil.writeValue(newTo2)))
                    .andDo(print())
                    .andExpect(status().isConflict());
        }

        @TestConfiguration
        static class TesClockConfig {

            @Bean
            @Primary
            public Clock fixedClock() {
                LocalTime time = LocalTime.parse("11:20");
                LocalDate date = LocalDate.parse("2022-01-01");
                LocalDateTime dateTime = LocalDateTime.of(date, time);
                return Clock.fixed(dateTime.toInstant(ZoneOffset.UTC), ZoneOffset.UTC);
            }
        }
    }

}
