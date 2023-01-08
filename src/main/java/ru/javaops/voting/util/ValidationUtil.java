package ru.javaops.voting.util;

import lombok.experimental.UtilityClass;
import ru.javaops.voting.error.IllegalRequestDataException;
import ru.javaops.voting.model.HasId;

@UtilityClass
public class ValidationUtil {
    public static void checkNew(HasId bean) {
        if (!bean.isNew()) {
            throw new IllegalRequestDataException(bean.getClass().getSimpleName() + " must be new (id=null)");
        }
    }
}
