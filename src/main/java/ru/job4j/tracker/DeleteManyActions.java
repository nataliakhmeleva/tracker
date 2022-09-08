package ru.job4j.tracker;

import java.util.List;

public class DeleteManyActions implements UserAction {
    private final Output out;

    public DeleteManyActions(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Delete all actions";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Delete all items ====");
        List<Item> items = tracker.findAll();
        for (Item item : items) {
            tracker.delete(item.getId());
        }
        out.println("success");
        return true;
    }
}
