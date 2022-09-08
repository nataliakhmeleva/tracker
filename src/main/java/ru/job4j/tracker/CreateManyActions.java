package ru.job4j.tracker;

public class CreateManyActions implements UserAction {
    private final Output out;

    public CreateManyActions(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Create many actions";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Create many items ====");
        String name = input.askStr("Enter name: ");
        int count = input.askInt("Enter count: ");
        for (int i = 0; i < count; i++) {
            Item item = new Item(name + i);
            tracker.add(item);
        }
        out.println("success");
        return true;
    }
}
