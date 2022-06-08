package ru.job4j.tracker;

import java.util.List;

public class FindByNameAction implements UserAction {
    private final Output out;

    public FindByNameAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Find by name";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Find items by name ====");
        String name = input.askStr("Enter name: ");
        List<Item> item = tracker.findByName(name);
        if (item.size() > 0) {
            for (Item items : item) {
                out.println(items);
            }
        } else {
            out.println("Заявки с таким именем не найдены");
        }
        return true;
    }
}
