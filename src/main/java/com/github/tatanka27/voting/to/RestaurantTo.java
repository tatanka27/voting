package com.github.tatanka27.voting.to;

import com.github.tatanka27.voting.model.ItemMenu;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;

@Value
@EqualsAndHashCode(callSuper = true)
public class RestaurantTo extends NamedTo {

    List<ItemMenu> menu;

    public RestaurantTo(Integer id, String name, List<ItemMenu> menu) {
        super(id, name);
        this.menu = menu;
    }
}
