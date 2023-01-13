package ru.javaops.voting.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javaops.voting.model.Menu;
import ru.javaops.voting.repository.MenuRepository;
import ru.javaops.voting.to.MenuTo;
import ru.javaops.voting.util.JsonUtil;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.voting.data.DishTestData.dish1;
import static ru.javaops.voting.data.DishTestData.dish4;
import static ru.javaops.voting.data.MenuTestDate.MENU_MATCHER;
import static ru.javaops.voting.data.MenuTestDate.menu1;
import static ru.javaops.voting.data.RestaurantTestData.restaurant1;
import static ru.javaops.voting.data.UserTestData.ADMIN_MAIL;
import static ru.javaops.voting.web.controller.AdminMenuController.REST_URL;

public class AdminMenuControllerTest extends AbstractControllerTest {

    @Autowired
    MenuRepository menuRepository;

    @Test
    void getUnauthorized() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void addMenu() throws Exception {
        MenuTo menuTo = new MenuTo(null, 1, 4);
        Menu newMenu = new Menu(null, LocalDate.now(), restaurant1, dish4);

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
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(duplicate)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void addInvalidMenu() throws Exception {
        MenuTo invalid = new MenuTo(null, null, dish1.getId());
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}
