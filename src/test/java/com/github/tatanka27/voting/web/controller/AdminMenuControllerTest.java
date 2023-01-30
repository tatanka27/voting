package com.github.tatanka27.voting.web.controller;

import com.github.tatanka27.voting.data.DishTestData;
import com.github.tatanka27.voting.data.RestaurantTestData;
import com.github.tatanka27.voting.model.Menu;
import com.github.tatanka27.voting.repository.MenuRepository;
import com.github.tatanka27.voting.to.MenuTo;
import com.github.tatanka27.voting.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.github.tatanka27.voting.data.MenuTestDate.MENU_MATCHER;
import static com.github.tatanka27.voting.data.MenuTestDate.menu1;
import static com.github.tatanka27.voting.data.UserTestData.ADMIN_MAIL;

public class AdminMenuControllerTest extends AbstractControllerTest {

    @Autowired
    MenuRepository menuRepository;

    @Test
    void getUnauthorized() throws Exception {
        perform(MockMvcRequestBuilders.get(AdminMenuController.REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void addMenu() throws Exception {
        MenuTo menuTo = new MenuTo(null, 1, 4);
        Menu newMenu = new Menu(null, LocalDate.now(), RestaurantTestData.restaurant1, DishTestData.dish4);

        ResultActions action = perform(MockMvcRequestBuilders.post(VoteController.REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(menuTo)));

        Menu created = MENU_MATCHER.readFromJson(action);
        int newId = created.id();
        newMenu.setId(newId);
        MENU_MATCHER.assertMatch(created, newMenu);
        MENU_MATCHER.assertMatch(menuRepository.getExisted(newId), newMenu);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void addMenuDuplicate() throws Exception {
        MenuTo duplicate = new MenuTo(null, menu1.getRestaurant().id(), menu1.getDish().id());
        perform(MockMvcRequestBuilders.post(AdminMenuController.REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(duplicate)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void addInvalidMenu() throws Exception {
        MenuTo invalid = new MenuTo(null, null, DishTestData.dish1.getId());
        perform(MockMvcRequestBuilders.post(AdminMenuController.REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}
