package ru.job4j.collection.exam;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Article {

    public static boolean generateBy(String origin, String line) {
        Set<String> originSet = new HashSet<>(
            Arrays.asList(origin.split("\\s*(\\s|,|!|;|:|\\.)\\s*")));
        Set<String> lineSet = new HashSet<>(
            Arrays.asList(line.split("\\s*(\\s|,|!|;|:|\\.)\\s*")));
        return originSet.containsAll(lineSet);
    }
}

