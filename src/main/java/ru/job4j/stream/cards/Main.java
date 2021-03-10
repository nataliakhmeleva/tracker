package ru.job4j.stream.cards;

import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Stream.of(Suit.values())
            .flatMap(suit -> Stream.of(Value.values())
                .map(value -> value + " " + suit))
            .forEach(System.out::println);
    }
}
