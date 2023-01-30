package com.github.tatanka27.voting.util;

import com.github.tatanka27.voting.HasId;
import com.github.tatanka27.voting.error.IllegalRequestDataException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationUtil {
    public static void checkNew(HasId bean) {
        if (!bean.isNew()) {
            throw new IllegalRequestDataException(bean.getClass().getSimpleName() + " must be new (id=null)");
        }
    }

    public static <T> T checkExisted(T obj, int id) {
        if (obj == null) {
            throw new IllegalRequestDataException("Entity with id=" + id + " not found");
        }
        return obj;
    }
}
