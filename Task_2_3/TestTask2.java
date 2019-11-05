package Lesson_6.Task_2_3;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestTask2 {

    ForTests forTests;

    @Before
    public void init() {
        forTests = new ForTests();
    }

    @Test
    public void test1() {
        int[] ARRAY = {1, 2, 4, 4, 2, 3, 4, 1, 7};
        int[] EXPECTED = {1, 7};
        Assert.assertArrayEquals(EXPECTED, forTests.TASK2(ARRAY));
    }

    @Test
    public void test2() {
        int[] ARRAY = {4, 2, 9, 0, 2, 0, 1, 1, 7};
        int[] EXPECTED = {2, 9, 0, 2, 0, 1, 1, 7};
        Assert.assertArrayEquals(EXPECTED, forTests.TASK2(ARRAY));
    }

    @Test (expected = RuntimeException.class)
    public void test3() {
        int[] ARRAY = {2, 2, 9, 0, 2, 0, 1, 1, 7};
        int[] EXPECTED = {2, 9, 0, 2, 0, 1, 1, 7};
        Assert.assertArrayEquals(EXPECTED, forTests.TASK2(ARRAY));
    }
}