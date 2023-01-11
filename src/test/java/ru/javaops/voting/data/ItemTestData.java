package ru.javaops.voting.data;

import ru.javaops.voting.model.Item;
import ru.javaops.voting.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

public class ItemTestData {
    public static final int ITEM_ID = 1;

    public static Item item1 = new Item(ITEM_ID, "Салат", 100.0, LocalDate.now());
    public static Item item2 = new Item(ITEM_ID + 1, "Курица", 200.0, LocalDate.now());
    public static Item item3 = new Item(ITEM_ID + 2, "Брускета", 150.0, LocalDate.now());
    public static Item item4 = new Item(ITEM_ID + 3, "Суп", 100.0, LocalDate.now());
    public static Item item5 = new Item(ITEM_ID + 4, "Десерт", 100.0, LocalDate.now().minusDays(1));
    public static Item item6 = new Item(ITEM_ID + 5, "Омлет", 100.0, LocalDate.now().minusDays(1));

    public static List<Item> menu1 = List.of(item1, item2);
    public static List<Item> menu2 = List.of(item3, item4, item5);
    public static List<Item> menu3 = List.of(item6);

    public static Restaurant getNew() {
        return new Restaurant(null, "New");
    }
}
