package ru.javaops.voting.to;

import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.javaops.voting.model.Menu;

import java.util.List;

@Value
@EqualsAndHashCode(callSuper = true)
public class RestaurantTo extends NamedTo {

    List<Menu> menuList;

    public RestaurantTo(Integer id, String name, List<Menu> menuList) {
        super(id, name);
        this.menuList = menuList;
    }
}
