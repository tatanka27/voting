package ru.javaops.voting.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javaops.voting.model.Vote;
import ru.javaops.voting.repository.VoteRepository;
import ru.javaops.voting.to.VoteTo;
import ru.javaops.voting.util.JsonUtil;

import java.time.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.voting.data.RestaurantTestData.RESTAURANT1_ID;
import static ru.javaops.voting.data.RestaurantTestData.restaurant1;
import static ru.javaops.voting.data.UserTestData.USER_MAIL;
import static ru.javaops.voting.data.UserTestData.user;
import static ru.javaops.voting.data.VoteTestData.VOTE_MATCHER;
import static ru.javaops.voting.web.controller.VoteController.REST_URL;

public class VoteControllerTest {

    protected static class VoteBefore11Test extends AbstractControllerTest {

        @Autowired
        VoteRepository voteRepository;

        @Test
        void getUnauthorized() throws Exception {
            perform(MockMvcRequestBuilders.get(REST_URL))
                    .andExpect(status().isUnauthorized());
        }

        @Test
        @WithUserDetails(value = USER_MAIL)
        void addVote() throws Exception {
            VoteTo newTo = new VoteTo(null, RESTAURANT1_ID);
            Vote newVote = new Vote(null, LocalDate.now(), user, restaurant1);

            ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtil.writeValue(newTo)));

            Vote created = VOTE_MATCHER.readFromJson(action);
            int newId = created.id();
            newVote.setId(newId);
            VOTE_MATCHER.assertMatch(created, newVote);
            VOTE_MATCHER.assertMatch(voteRepository.getExisted(newId), newVote);
        }

        @TestConfiguration
        static class TesClockConfig {

            @Bean
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
        void addInvalidVote() throws Exception {
            VoteTo invalid = new VoteTo(null, null);
            perform(MockMvcRequestBuilders.post(REST_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtil.writeValue(invalid)))
                    .andDo(print())
                    .andExpect(status().isUnprocessableEntity());
        }

        @TestConfiguration
        static class TesClockConfig {

            @Bean
            @Primary
            public Clock fixedClock() {
                LocalTime time = LocalTime.parse("15:00");
                LocalDate date = LocalDate.parse("2022-01-01");
                LocalDateTime dateTime = LocalDateTime.of(date, time);
                return Clock.fixed(dateTime.toInstant(ZoneOffset.UTC), ZoneOffset.UTC);
            }
        }
    }

}
