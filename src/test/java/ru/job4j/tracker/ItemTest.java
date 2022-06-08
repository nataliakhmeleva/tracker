package ru.job4j.tracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ItemTest {

    @Test
    public void whenSortItem() {
        List<Item> items = new ArrayList<>(List.of(new Item(2, "Item name2", LocalDateTime.now()),
            new Item(1, "Item name1", LocalDateTime.now())
        ));
        Collections.sort(items, new SortItem());
        assertThat(items.toString(),
            is("[id: 1, name: Item name1, created: " + LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))
                + ", id: 2, name: Item name2, created: " + LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + "]"));
    }

    @Test
    public void whenReverseItem() {
        List<Item> items = new ArrayList<>(List.of(new Item(2, "Item name2", LocalDateTime.now()),
            new Item(1, "Item name1", LocalDateTime.now())
        ));
        Collections.sort(items, new ReverseItem());
        assertThat(items.toString(),
            is("[id: 2, name: Item name2, created: " + LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))
                + ", id: 1, name: Item name1, created: " + LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + "]"));
    }
}