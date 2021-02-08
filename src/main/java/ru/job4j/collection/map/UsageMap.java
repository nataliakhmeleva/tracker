package ru.job4j.collection.map;

import java.util.HashMap;

public class UsageMap {
    public static void main(String[] args) {
        HashMap<String, String> pair = new HashMap<>();
        pair.put("nataliya-mar@mail.ru", "Khmeleva Natalia Sergeevna");
        for (String key : pair.keySet()) {
            String value = pair.get(key);
            System.out.println(key + " = " + value);
        }
    }
}
