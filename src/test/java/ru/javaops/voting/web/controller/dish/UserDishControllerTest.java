package ru.javaops.voting.web.controller.dish;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javaops.voting.web.controller.AbstractControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.voting.data.DishTestData.DISH_MATCHER;
import static ru.javaops.voting.data.DishTestData.menu2Today;
import static ru.javaops.voting.data.RestaurantTestData.RESTAURANT2_ID;
import static ru.javaops.voting.data.UserTestData.USER_MAIL;
import static ru.javaops.voting.web.controller.dish.UserDishController.REST_URL;

public class UserDishControllerTest extends AbstractControllerTest {

    private static final String REST_URL_REST2 = REST_URL.replace("{id}", String.valueOf(RESTAURANT2_ID));

    @Test
    void getUnauthorized() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_REST2))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_REST2))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(menu2Today));
    }
}
