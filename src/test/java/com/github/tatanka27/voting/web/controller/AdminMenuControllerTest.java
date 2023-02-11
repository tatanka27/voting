package com.github.tatanka27.voting.web.controller;

import com.github.tatanka27.voting.model.ItemMenu;
import com.github.tatanka27.voting.repository.ItemMenuRepository;
import com.github.tatanka27.voting.to.ItemMenuTo;
import com.github.tatanka27.voting.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static com.github.tatanka27.voting.data.DishTestData.*;
import static com.github.tatanka27.voting.data.MenuTestData.MENU_MATCHER;
import static com.github.tatanka27.voting.data.MenuTestData.itemMenu1;
import static com.github.tatanka27.voting.data.RestaurantTestData.RESTAURANT1_ID;
import static com.github.tatanka27.voting.data.UserTestData.ADMIN_MAIL;
import static com.github.tatanka27.voting.web.controller.AdminMenuController.REST_URL;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdminMenuControllerTest extends AbstractControllerTest {

    @Autowired
    ItemMenuRepository menuRepository;

    @Test
    void getUnauthorized() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL, RESTAURANT1_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void create() throws Exception {
        ItemMenuTo newTo = new ItemMenuTo(null, DISH2_ID, LocalDate.now());
        ItemMenu newItemMenu = new ItemMenu(null, newTo.getDateMenu(), dish2);

        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL, RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newTo)));

        ItemMenu created = MENU_MATCHER.readFromJson(action);
        int newId = created.id();
        newItemMenu.setId(newId);
        MENU_MATCHER.assertMatch(created, newItemMenu);
        MENU_MATCHER.assertMatch(menuRepository.getExisted(newId), newItemMenu);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createDuplicate() throws Exception {
        ItemMenuTo duplicate = new ItemMenuTo(null, itemMenu1.getDish().id(), itemMenu1.getDateMenu());
        perform(MockMvcRequestBuilders.post(REST_URL, RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(duplicate)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createInvalid() throws Exception {
        ItemMenuTo invalid = new ItemMenuTo(null, DISH1_ID, null);
        perform(MockMvcRequestBuilders.post(REST_URL, RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}
