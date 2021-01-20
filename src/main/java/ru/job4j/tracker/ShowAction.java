package ru.job4j.tracker;

public class ShowAction implements UserAction{
    @Override
    public String name() {
        return "=== Show all items ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        for (Item items : tracker.findAll()) {
            System.out.println(items);
        }
        return true;
    }
}
