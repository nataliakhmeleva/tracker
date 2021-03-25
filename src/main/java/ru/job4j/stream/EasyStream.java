package ru.job4j.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class EasyStream {

    private final List<Integer> list;

    public EasyStream(List<Integer> list) {
        this.list = list;
    }

    public static EasyStream of(List<Integer> source) {
        return new EasyStream(source);
    }

    public EasyStream map(Function<Integer, Integer> fun) {
        List<Integer> rsl = new ArrayList<>();
        for (Integer listMap : list) {
            rsl.add(fun.apply(listMap));
        }
        return EasyStream.of(rsl);
    }

    public EasyStream filter(Predicate<Integer> fun) {
        List<Integer> rsl = new ArrayList<>();
        for (Integer listFilter : list) {
            if (fun.test(listFilter)) {
                rsl.add(listFilter);
            }
        }
        return EasyStream.of(rsl);
    }

    public List<Integer> collect() {
        return list;
    }
}
