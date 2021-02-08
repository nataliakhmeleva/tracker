package ru.job4j.collection.set;

import java.util.HashSet;

public class UniqueText {
    public static boolean isEquals(String originText, String duplicateText) {
        boolean rsl = true;
        String[] origin = originText.split(" ");
        String[] text = duplicateText.split(" ");
        HashSet<String> check = new HashSet<>();
        for (String sets : origin) {
            check.add(sets);
        }
        for (String sets : text) {
            if (!check.contains(sets)) {
                rsl = false;
                break;
            }
        }
        return rsl;
    }
}
