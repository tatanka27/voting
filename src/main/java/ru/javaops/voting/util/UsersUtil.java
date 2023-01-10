package ru.javaops.voting.util;

import lombok.experimental.UtilityClass;
import ru.javaops.voting.model.Role;
import ru.javaops.voting.model.User;
import ru.javaops.voting.to.UserTo;

import static ru.javaops.voting.config.SecurityConfiguration.PASSWORD_ENCODER;

@UtilityClass
public class UsersUtil {
    public static User prepareToSave(User user) {
        user.setPassword(PASSWORD_ENCODER.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }

    public static User createNewFromTo(UserTo userTo) {
        return new User(null, userTo.getName(), userTo.getEmail().toLowerCase(), userTo.getPassword(), Role.USER);
    }

}
