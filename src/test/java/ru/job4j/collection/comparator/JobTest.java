package ru.job4j.collection.comparator;

import org.junit.Test;
import java.util.Comparator;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class JobTest {
    @Test
    public void whenComparatorBySortName() {
        Comparator<Job> comparator = new SortName();
        int rsl = comparator.compare(
                new Job("hello", 1),
                new Job("bye", 2)
        );
        assertThat(rsl,greaterThan(0));
    }

    @Test
    public void whenComparatorByReverseName() {
        Comparator<Job> comparator = new ReverseName();
        int rsl = comparator.compare(
                new Job("hello", 1),
                new Job("bye", 2)
        );
        assertThat(rsl,lessThan(0));
    }

    @Test
    public void whenComparatorBySortPriority() {
        Comparator<Job> comparator = new SortPriority();
        int rsl = comparator.compare(
                new Job("hello", 1),
                new Job("bye", 2)
        );
        assertThat(rsl,lessThan(0));
    }

    @Test
    public void whenComparatorByReversePriority() {
        Comparator<Job> comparator = new ReversePriority();
        int rsl = comparator.compare(
                new Job("hello", 1),
                new Job("bye", 2)
        );
        assertThat(rsl,greaterThan(0));
    }

    @Test
    public void whenComparatorBySortNameAndSortPriority() {
        Comparator<Job> cmpNamePriority = new SortName().thenComparing(new SortPriority());
        int rsl = cmpNamePriority.compare(
                new Job("hello", 1),
                new Job("hello", 2)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenComparatorBySortPriorityAndReverseName() {
        Comparator<Job> cmpNamePriority = new SortPriority().thenComparing(new ReverseName());
        int rsl = cmpNamePriority.compare(
                new Job("hello", 2),
                new Job("bye", 2)
        );
        assertThat(rsl, lessThan(0));
    }
}