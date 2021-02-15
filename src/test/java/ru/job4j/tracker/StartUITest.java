package ru.job4j.tracker;


import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

public class StartUITest {

    @Test
    public void whenAddItem() {
        Output output = new StubOutput();
        Input input = new StubInput(new String[]{"0", "Item name", "1"});
        Tracker tracker = new Tracker();
        List<UserAction> actions = List.of(new CreateAction(output), new ExitAction());
        new StartUI(output).init(input, tracker, actions);
        assertThat(tracker.findAll().get(0).getName(), is("Item name"));
    }

    @Test
    public void whenShowAllItem() {
        Output output = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Item name"));
        Input input = new StubInput(new String[]{"0", "1"});
        List<UserAction> actions = List.of(new ShowAction(output), new ExitAction());
        new StartUI(output).init(input, tracker, actions);
        assertThat(output.toString(),
            is("Menu." + System.lineSeparator() + "0. Show all" + System.lineSeparator() + "1. Exit"
                + System.lineSeparator() + "=== Show all items ====" + System.lineSeparator() + item
                .toString() + System.lineSeparator() + "Menu." + System.lineSeparator()
                + "0. Show all" + System.lineSeparator() + "1. Exit" + System.lineSeparator()));
    }

    @Test
    public void whenReplaceItem() {
        Output output = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("new"));
        String replacedName = "replaced";
        Input input = new StubInput(
            new String[]{"0", String.valueOf(item.getId()), replacedName, "1"});
        List<UserAction> actions = List.of(new ReplaceAction(output), new ExitAction());
        new StartUI(output).init(input, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName(), is("replaced"));
    }

    @Test
    public void whenDeleteItem() {
        Output output = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("new"));
        Input input = new StubInput(new String[]{"0", String.valueOf(item.getId()), "1"});
        List<UserAction> actions = List.of(new DeleteAction(output), new ExitAction());
        new StartUI(output).init(input, tracker, actions);
        assertThat(tracker.findById(item.getId()), is(nullValue()));
    }

    @Test
    public void whenFindItemById() {
        Output output = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Item name"));
        Input input = new StubInput(new String[]{"0", String.valueOf(item.getId()), "1"});
        List<UserAction> actions = List.of(new FindByIdAction(output), new ExitAction());
        new StartUI(output).init(input, tracker, actions);
        assertThat(output.toString(),
            is("Menu." + System.lineSeparator() + "0. Find by Id" + System.lineSeparator()
                + "1. Exit" + System.lineSeparator() + "=== Find item by Id ====" + System
                .lineSeparator() + item.toString() + System.lineSeparator() + "Menu." + System
                .lineSeparator() + "0. Find by Id" + System.lineSeparator() + "1. Exit" + System
                .lineSeparator()));
    }

    @Test
    public void whenFindItemByName() {
        Output output = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Item name"));
        Input input = new StubInput(new String[]{"0", item.getName(), "1"});
        List<UserAction> actions = List.of(new FindByNameAction(output), new ExitAction());
        new StartUI(output).init(input, tracker, actions);
        assertThat(output.toString(),
            is("Menu." + System.lineSeparator() + "0. Find by name" + System.lineSeparator()
                + "1. Exit" + System.lineSeparator()
                + "=== Find items by name ====" + System.lineSeparator()
                + item.toString() + System.lineSeparator() + "Menu." + System
                .lineSeparator() + "0. Find by name" + System.lineSeparator() + "1. Exit" + System
                .lineSeparator()));
    }

    @Test
    public void whenExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
            new String[]{"0"}
        );
        Tracker tracker = new Tracker();
        List<UserAction> actions = List.of(new ExitAction());
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
            "Menu." + System.lineSeparator() + "0. Exit" + System.lineSeparator()
        ));
    }

    @Test
    public void whenInvalidExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
            new String[]{"1", "0"}
        );
        Tracker tracker = new Tracker();
        List<UserAction> actions = List.of(new ExitAction());
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
            String.format(
                "Menu.%n" + "0. Exit%n" + "Wrong input, you can select: 0 .. 0%n" + "Menu.%n"
                    + "0. Exit%n"
            )
        ));
    }
}