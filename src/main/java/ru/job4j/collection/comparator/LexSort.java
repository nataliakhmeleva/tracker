package ru.job4j.collection.comparator;

import java.util.Comparator;

public class LexSort implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        return Integer.compare(Integer.parseInt(left.substring(0, left.indexOf(" ")-1)),
                Integer.parseInt(right.substring(0, right.indexOf(" ")-1)));
    }
}
