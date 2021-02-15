package ru.job4j.collection.comparator;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int length = Math.min(left.length(), right.length());
        for (int i = 0; i < length; i++) {
            char lefts = left.charAt(i);
            char rights = right.charAt(i);
            if (lefts != rights){
                return Character.compare(lefts,rights);
            }
        }
        return Integer.compare(left.length(), right.length());
    }
}
