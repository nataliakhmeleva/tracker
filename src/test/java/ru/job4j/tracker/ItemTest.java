package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ItemTest {
    @Test
    public void whenSortItem() {
        List<Item> items = new ArrayList<>(List.of(new Item(2, "Item name2"),
                new Item(1, "Item name1")
        ));
        Collections.sort(items, new SortItem());
        assertThat(items.toString(), is("[Item{id=1, name='Item name1'}, " +
                "Item{id=2, name='Item name2'}]"));
    }

    @Test
    public void whenReverseItem() {
        List<Item> items = new ArrayList<>(List.of(new Item(2, "Item name2"),
                new Item(1, "Item name1")
        ));
        Collections.sort(items, new ReverseItem());
        assertThat(items.toString(), is("[Item{id=2, name='Item name2'}, " +
                "Item{id=1, name='Item name1'}]"));
    }
}