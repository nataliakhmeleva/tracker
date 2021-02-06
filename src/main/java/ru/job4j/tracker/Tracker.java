package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Tracker {
    private static Tracker instance = null;

    private final List<Item> items = new ArrayList<>();
    private int ids = 1;
    private int size = 0;

    private Tracker() {
    }

    public static Tracker getInstance(){
        if (instance == null){
            instance = new Tracker();
        }
        return instance;
    }

    public Item add(Item item) {
        item.setId(ids++);
        items.set(size++, item);
        return item;
    }

    public Item findById(int id) {
        int index = indexOf(id);
        return index != -1 ? items.get(index) : null;
    }

    public List<Item> findAll() {
        return List.copyOf(items);
    }

    public Item[] findByName(String key) {
        int count = 0;
        Item[] itemsResult = new Item[items.size()];
        for (int i = 0; i < size; i++) {
            if (items.get(i).getName().equals(key)) {
                itemsResult[count++] = items.get(i);
            }
        }
        return Arrays.copyOf(itemsResult, count);
    }

    private int indexOf(int id) {
        int rsl = -1;
        for (int index = 0; index < size; index++) {
            if (items.get(index).getId() == id) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }

    public boolean replace(int id, Item item) {
        int index = indexOf(id);
        boolean rsl = index != -1;
        if (rsl) {
            item.setId(id);
            items.set(index, item);
        }
        return rsl;
    }

    public boolean delete(int id) {
        int index = indexOf(id);
        boolean rsl = index != -1;
        if (rsl) {
            System.arraycopy(items, index + 1, items, index, size - index);
            items.set(size - 1, null);
            size--;
        }
        return rsl;
    }
}