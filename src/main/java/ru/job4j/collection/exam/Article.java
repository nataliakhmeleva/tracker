package ru.job4j.collection.exam;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Article {

    public static boolean generateBy(String origin, String line) {
        Set<String> originSet = new HashSet<>(
            Arrays.asList(origin.split("\\s*(\\s|,|!|;|:|\\.)\\s*")));
        for (String sets : line.split("\\s*(\\s|,|!|;|:|\\.)\\s*")) {
            if (!originSet.contains(sets)) {
                return false;
            }
        }
        return true;
    }
}

