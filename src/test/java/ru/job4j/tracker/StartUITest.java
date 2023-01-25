package ru.job4j.tracker;


import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StartUITest {

    @Test
    public void whenAddItem() {
        Output output = new StubOutput();
        Input input = new StubInput(new String[]{"0", "Item name", "1"});
        MemTracker tracker = new MemTracker();
        List<UserAction> actions = List.of(new CreateAction(output), new ExitAction());
        new StartUI(output).init(input, tracker, actions);
        assertThat(tracker.findAll().get(0).getName(), is("Item name"));
    }

    @Test
    public void whenShowAllItem() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = tracker.add(new Item("Item name"));
        Input input = new StubInput(new String[]{"0", "1"});
        List<UserAction> actions = List.of(new ShowAction(output), new ExitAction());
        new StartUI(output).init(input, tracker, actions);
        assertThat(output.toString(),
                is("Menu." + System.lineSeparator() + "0. Show all" + System.lineSeparator()
                        + "1. Exit" + System.lineSeparator() + "=== Show all items ===="
                        + System.lineSeparator() + item + System.lineSeparator()
                        + "Menu." + System.lineSeparator() + "0. Show all"
                        + System.lineSeparator() + "1. Exit" + System.lineSeparator()));
    }

    @Test
    public void whenReplaceItem() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
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
        MemTracker tracker = new MemTracker();
        Item item = tracker.add(new Item("new"));
        Input input = new StubInput(new String[]{"0", String.valueOf(item.getId()), "1"});
        List<UserAction> actions = List.of(new DeleteAction(output), new ExitAction());
        new StartUI(output).init(input, tracker, actions);
        assertThat(tracker.findById(item.getId()), is(nullValue()));
    }

    @Test
    public void whenFindItemById() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = tracker.add(new Item("Item name"));
        Input input = new StubInput(new String[]{"0", String.valueOf(item.getId()), "1"});
        List<UserAction> actions = List.of(new FindByIdAction(output), new ExitAction());
        new StartUI(output).init(input, tracker, actions);
        assertThat(output.toString(),
                is("Menu." + System.lineSeparator() + "0. Find by Id" + System.lineSeparator()
                        + "1. Exit" + System.lineSeparator() + "=== Find item by Id ===="
                        + System.lineSeparator() + item + System.lineSeparator() + "Menu."
                        + System.lineSeparator() + "0. Find by Id" + System.lineSeparator()
                        + "1. Exit" + System.lineSeparator()));
    }

    @Test
    public void whenFindItemByName() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = tracker.add(new Item("Item name"));
        Input input = new StubInput(new String[]{"0", item.getName(), "1"});
        List<UserAction> actions = List.of(new FindByNameAction(output), new ExitAction());
        new StartUI(output).init(input, tracker, actions);
        assertThat(output.toString(),
                is("Menu." + System.lineSeparator() + "0. Find by name" + System.lineSeparator()
                        + "1. Exit" + System.lineSeparator()
                        + "=== Find items by name ====" + System.lineSeparator()
                        + item + System.lineSeparator() + "Menu." + System.lineSeparator()
                        + "0. Find by name" + System.lineSeparator() + "1. Exit"
                        + System.lineSeparator()));
    }

    @Test
    public void whenExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"0"}
        );
        MemTracker tracker = new MemTracker();
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
        MemTracker tracker = new MemTracker();
        List<UserAction> actions = List.of(new ExitAction());
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                String.format(
                        "Menu.%n" + "0. Exit%n" + "Wrong input, you can select: 0 .. 0%n"
                                + "Menu.%n" + "0. Exit%n"
                )
        ));
    }

    @Test
    public void whenMockReplaceItem() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        ReplaceAction rep = new ReplaceAction(out);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(1);
        when(input.askStr(any(String.class))).thenReturn(replacedName);

        rep.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Edit item ====" + ln + "Изменения внесены." + ln));
        assertThat(tracker.findAll().get(0).getName(), is(replacedName));
    }

    @Test
    public void whenMockDeleteItem() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = tracker.add(new Item("Deleted item"));
        DeleteAction del = new DeleteAction(out);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(1);
        del.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Delete item ====" + ln + "Изменения внесены." + ln));
        assertThat(tracker.findById(item.getId()), is(nullValue()));
    }

    @Test
    public void whenMockFindItemById() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = tracker.add(new Item("Found by id item"));
        FindByIdAction findId = new FindByIdAction(out);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(1);
        findId.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Find item by Id ====" + ln + item + ln));
        assertThat(tracker.findById(item.getId()), is(item));
    }

    @Test
    public void whenMockFindItemByName() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = tracker.add(new Item("Found by name item"));
        String foundName = "Found by name item";
        FindByNameAction findId = new FindByNameAction(out);

        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn(foundName);
        findId.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Find items by name ====" + ln + item + ln));
        assertThat(tracker.findAll().get(0).getName(), is(foundName));
    }
}