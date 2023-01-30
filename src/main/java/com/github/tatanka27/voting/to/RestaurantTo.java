package com.github.tatanka27.voting.to;

import com.github.tatanka27.voting.model.Menu;
import lombok.EqualsAndHashCode;
import lombok.Value;

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
