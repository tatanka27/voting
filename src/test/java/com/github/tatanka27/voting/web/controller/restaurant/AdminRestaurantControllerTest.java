package com.github.tatanka27.voting.web.controller.restaurant;

import com.github.tatanka27.voting.data.RestaurantTestData;
import com.github.tatanka27.voting.to.RestaurantTo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.github.tatanka27.voting.model.Restaurant;
import com.github.tatanka27.voting.repository.RestaurantRepository;
import com.github.tatanka27.voting.util.JsonUtil;
import com.github.tatanka27.voting.util.RestaurantUtil;
import com.github.tatanka27.voting.web.controller.AbstractControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.github.tatanka27.voting.data.MenuTestDate.menus2Today;
import static com.github.tatanka27.voting.data.UserTestData.ADMIN_MAIL;
import static com.github.tatanka27.voting.data.UserTestData.USER_MAIL;
import static com.github.tatanka27.voting.web.controller.restaurant.AdminRestaurantController.REST_URL;

public class AdminRestaurantControllerTest extends AbstractControllerTest {

    private static final String REST_URL_SLASH = REST_URL + '/';

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    void getUnauthorized() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getForbidden() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + RestaurantTestData.RESTAURANT_NOT_FOUND_ID))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RestaurantTestData.RESTAURANT_MATCHER.contentJson(RestaurantTestData.restaurants));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + RestaurantTestData.RESTAURANT1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RestaurantTestData.RESTAURANT_MATCHER.contentJson(RestaurantTestData.restaurant1));
    }

//    @Ignore
    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getWithMenu() throws Exception {
        RestaurantTo restaurantTo = RestaurantUtil.createTo(RestaurantTestData.restaurant2, menus2Today);
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + RestaurantTestData.RESTAURANT2_ID + "/with-menu"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RestaurantTestData.RESTAURANT_WITH_MENU_MATCHER.contentJson(restaurantTo));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void create() throws Exception {
        Restaurant newRestaurant = RestaurantTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newRestaurant)));

        Restaurant created = RestaurantTestData.RESTAURANT_MATCHER.readFromJson(action);
        int newId = created.id();
        newRestaurant.setId(newId);
        RestaurantTestData.RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RestaurantTestData.RESTAURANT_MATCHER.assertMatch(restaurantRepository.getExisted(newId), newRestaurant);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createInvalid() throws Exception {
        Restaurant invalid = new Restaurant(null, null);
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}
