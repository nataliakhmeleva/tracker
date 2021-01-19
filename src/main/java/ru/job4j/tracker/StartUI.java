package ru.job4j.tracker;


public class StartUI {
    public void init(Input input, Tracker tracker) {
        boolean run = true;
        while (run) {
            this.showMenu();
            //System.out.print("Select: ");
            int select = input.askInt("Select: ");
            if (select == 0) {
                System.out.println("=== Create a new Item ====");
                //System.out.print("Enter name: ");
                String name = input.askStr("Enter name: ");
                Item item = new Item(name);
                tracker.add(item);
            } else if (select == 1) {
                System.out.println("=== Show all items ====");
                for (Item items : tracker.findAll()) {
                    System.out.println(items);
                }
            } else if (select == 2) {
                System.out.println("=== Edit item ====");
                //System.out.print("Enter id: ");
                int id = input.askInt("Enter id: ");
                //System.out.print("Enter name: ");
                String name = input.askStr("Enter name: ");
                Item item = new Item(name);
                if (tracker.replace(id, item)) {
                    System.out.println("Изменения внесены.");
                } else {
                    System.out.println("Ошибка.");
                }
            } else if (select == 3) {
                System.out.println("=== Delete item ====");
                //System.out.print("Enter id: ");
                int id = input.askInt("Enter id: ");
                if (tracker.delete(id)) {
                    System.out.println("Изменения внесены.");
                } else {
                    System.out.println("Ошибка.");
                }
            } else if (select == 4) {
                System.out.println("=== Find item by Id ====");
                //System.out.print("Enter id: ");
                int id = input.askInt("Enter id: ");
                Item item = tracker.findById(id);
                if (item != null) {
                    System.out.println(item.toString());
                } else {
                    System.out.println("Заявка с таким id не найдена");
                }
            } else if (select == 5) {
                System.out.println("=== Find items by name ====");
                //System.out.print("Enter name: ");
                String name = input.askStr("Enter name: ");
                Item[] item = tracker.findByName(name);
                if (item.length > 0) {
                    for (Item items : item) {
                        System.out.println(items);
                    }
                } else {
                    System.out.println("Заявки с таким именем не найдены");
                }
            } else if (select == 6) {
                run = false;
            }
        }
    }

    private void showMenu() {
        System.out.println("Menu.\n" +
                "0. Add new Item\n" +
                "1. Show all items\n" +
                "2. Edit item\n" +
                "3. Delete item\n" +
                "4. Find item by Id\n" +
                "5. Find items by name\n" +
                "6. Exit Program");
    }

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Tracker tracker = new Tracker();
        new StartUI().init(input, tracker);
    }
}
