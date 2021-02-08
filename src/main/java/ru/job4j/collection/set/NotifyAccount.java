package ru.job4j.collection.set;

import java.util.HashSet;
import java.util.List;

public class NotifyAccount {
    public static HashSet<Account> sent(List<Account> accounts) {
        HashSet<Account> rsl = new HashSet<>();
        for (Account sets : accounts) {
            rsl.add(sets);
        }
        return rsl;
    }
}
