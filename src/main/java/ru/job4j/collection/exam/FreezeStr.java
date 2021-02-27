package ru.job4j.collection.exam;

import java.util.HashMap;
import java.util.Map;

public class FreezeStr {

    public static boolean eq(String left, String right) {
        Map<String, Integer> leftMap = words(left);
        Map<String, Integer> rightMap = words(right);
        return leftMap.equals(rightMap);
    }

    public static Map<String, Integer> words(String s) {
        Map<String, Integer> wordMap = new HashMap<>();
        for (String key : s.split("")) {
            int value = 1;
            if (wordMap.containsKey(key)) {
                value = wordMap.get(key) + 1;
            }
            wordMap.put(key, value);
        }
        return wordMap;
    }
}