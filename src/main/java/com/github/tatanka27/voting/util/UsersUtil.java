package com.github.tatanka27.voting.util;

import com.github.tatanka27.voting.config.SecurityConfiguration;
import com.github.tatanka27.voting.model.Role;
import com.github.tatanka27.voting.model.User;
import lombok.experimental.UtilityClass;
import com.github.tatanka27.voting.to.UserTo;

@UtilityClass
public class UsersUtil {
    public static User prepareToSave(User user) {
        user.setPassword(SecurityConfiguration.PASSWORD_ENCODER.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }

    public static User createNewFromTo(UserTo userTo) {
        return new User(null, userTo.getName(), userTo.getEmail().toLowerCase(), userTo.getPassword(), Role.USER);
    }

}
