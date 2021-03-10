package ru.job4j.stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MapStreamsTest {

    private List<Student> students = new ArrayList<>();

    @Before
    public void setUp() {
        students.add(new Student(10, "Surname1"));
        students.add(new Student(20, "Surname2"));
        students.add(new Student(30, "Surname3"));
        students.add(new Student(40, "Surname5"));
        students.add(new Student(40, "Surname5"));
        students.add(new Student(60, "Surname6"));
        students.add(new Student(70, "Surname7"));
        students.add(new Student(80, "Surname8"));
        students.add(new Student(90, "Surname9"));
    }

    @Test
    public void whenCollect() {
        MapStreams map = new MapStreams();
        Map<String, Student> rsl = map.collect(students);
        Map<String, Student> expected = new HashMap<>();
        expected.put("Surname1", new Student(10, "Surname1"));
        expected.put("Surname2", new Student(20, "Surname2"));
        expected.put("Surname3", new Student(30, "Surname3"));
        //expected.put("Surname5", new Student(40, "Surname5"));
        expected.put("Surname5", new Student(40, "Surname5"));
        expected.put("Surname6", new Student(60, "Surname6"));
        expected.put("Surname7", new Student(70, "Surname7"));
        expected.put("Surname8", new Student(80, "Surname8"));
        expected.put("Surname9", new Student(90, "Surname9"));
        assertThat(rsl, is(expected));
    }

}