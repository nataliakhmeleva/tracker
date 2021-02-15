package ru.job4j.collection.comparator;

import java.util.Comparator;

public class LexSort implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        String [] lefts = left.split("\\.");
        String [] rights = right.split("\\.");
        return Integer.compare(Integer.parseInt(lefts[0]), Integer.parseInt(rights[0]));
    }
}
