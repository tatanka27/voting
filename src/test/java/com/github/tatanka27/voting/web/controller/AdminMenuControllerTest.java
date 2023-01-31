package com.github.tatanka27.voting.web.controller;

import com.github.tatanka27.voting.data.DishTestData;
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

import static com.github.tatanka27.voting.data.MenuTestDate.MENU_MATCHER;
import static com.github.tatanka27.voting.data.MenuTestDate.menu1;
import static com.github.tatanka27.voting.data.UserTestData.ADMIN_MAIL;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdminMenuControllerTest extends AbstractControllerTest {

    @Autowired
    ItemMenuRepository menuRepository;

    @Test
    void getUnauthorized() throws Exception {
        perform(MockMvcRequestBuilders.get(AdminMenuController.REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void addMenu() throws Exception {
        ItemMenuTo menuTo = new ItemMenuTo(null, 4, LocalDate.now());
        ItemMenu newMenu = new ItemMenu(null, LocalDate.now(), DishTestData.dish4);

        ResultActions action = perform(MockMvcRequestBuilders.post(VoteController.REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(menuTo)));

        ItemMenu created = MENU_MATCHER.readFromJson(action);
        int newId = created.id();
        newMenu.setId(newId);
        MENU_MATCHER.assertMatch(created, newMenu);
        MENU_MATCHER.assertMatch(menuRepository.getExisted(newId), newMenu);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void addMenuDuplicate() throws Exception {
        ItemMenuTo duplicate = new ItemMenuTo(null, menu1.getDish().id(), menu1.getDateMenu());
        perform(MockMvcRequestBuilders.post(AdminMenuController.REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(duplicate)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void addInvalidMenu() throws Exception {
        ItemMenuTo invalid = new ItemMenuTo(null,  menu1.getDish().id(), menu1.getDateMenu());
        perform(MockMvcRequestBuilders.post(AdminMenuController.REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}
