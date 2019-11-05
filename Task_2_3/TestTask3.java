package Lesson_6.Task_2_3;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestTask3 {
    ForTests forTests;

    @Before
    public void init() {
        forTests = new ForTests();
    }

    @Test
    public void test1() {
        int[] ARRAY = {1, 2, 4, 4, 2, 3, 4, 1, 7};
        Assert.assertTrue(forTests.TASK3(ARRAY));
    }

    @Test
    public void test2() {
        int[] ARRAY = {2, 2, 2, 2, 1, 2, 2, 2, 2};
        Assert.assertFalse(forTests.TASK3(ARRAY));
    }

    @Test
    public void test3() {
        int[] ARRAY = {2, 2, 2, 2, 4, 2, 2, 2, 2};
        Assert.assertFalse(forTests.TASK3(ARRAY));
    }
}