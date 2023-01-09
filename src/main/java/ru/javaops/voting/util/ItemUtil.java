package ru.javaops.voting.util;

import lombok.experimental.UtilityClass;
import ru.javaops.voting.model.Item;
import ru.javaops.voting.to.ItemTo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ItemUtil {
    public static List<ItemTo> getTos(Collection<Item> items) {
        return items.stream()
                .map(ItemUtil::createTo)
                .collect(Collectors.toList());
    }

    public static ItemTo createTo(Item item) {
        return new ItemTo(item.getId(), item.getName(), item.getPrice());
    }
}
