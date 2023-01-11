package ru.javaops.voting.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javaops.voting.repository.RestaurantRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.voting.web.controller.AdminRestaurantController.REST_URL;


public class AdminRestaurantControllerTest extends AbstractControllerTest {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    void getUnauthorized() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized());
    }
}
