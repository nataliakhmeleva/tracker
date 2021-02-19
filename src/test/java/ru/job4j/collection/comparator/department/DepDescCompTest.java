package ru.job4j.collection.comparator.department;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class DepDescCompTest {

    @Test
    public void compare() {
        int rsl = new DepDescComp().compare(
            "K2/SK1/SSK2",
            "K2/SK1/SSK1"
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenUpDepartmentGoBefore() {
        int rsl = new DepDescComp().compare(
            "K2",
            "K2/SK1"
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenUpDepartmentGoBefore1() {
        int rsl = new DepDescComp().compare(
            "K1/SK1",
            "K2/SK1"
        );
        assertThat(rsl, greaterThan(0));
    }
}