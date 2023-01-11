package ru.javaops.voting.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.voting.data.RestaurantTestData.RESTAURANT_TO_MATCHER;
import static ru.javaops.voting.data.RestaurantTestData.restaurantsForToday;
import static ru.javaops.voting.data.UserTestData.USER_MAIL;
import static ru.javaops.voting.data.VoteTestData.votes;
import static ru.javaops.voting.util.RestaurantUtil.getTos;
import static ru.javaops.voting.web.controller.UserRestaurantController.REST_URL;

public class UserRestaurantControllerTest extends AbstractControllerTest {

    @Test
    void getUnauthorized() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_TO_MATCHER.contentJson(getTos(restaurantsForToday, votes)));
    }
}
