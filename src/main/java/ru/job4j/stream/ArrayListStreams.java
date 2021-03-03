package ru.job4j.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayListStreams {

    public static void main(String[] args) {
        ArrayList<Integer> array = new ArrayList<>(
            List.of(3, 4, 6, 9, 10, 15, 19, 25, 26, 31, 38, 46, 51));
        List<Integer> list = array.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
    }
}
