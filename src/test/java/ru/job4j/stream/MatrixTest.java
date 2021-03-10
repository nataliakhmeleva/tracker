package ru.job4j.stream;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class MatrixTest {

    @Test
    public void test() {
        assertEquals(List.of(1, 2, 3, 4, 5),
            Matrix.matrixs(new Integer[][]{
                {1, 2, 3},
                {4, 5}
            }));
    }
}