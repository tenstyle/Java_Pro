package Lesson_6.Task_2_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ForTests {

    public static void main(String[] args) {
    }


    public int[] TASK2(int[] array) {
        int index = array.length - 1;
        int[] tmp;

        while (index >= 0) {
            if (array[index] == 4) break;
            index--;
        }

        if (index <= -1) throw new RuntimeException();

        tmp = new int[(array.length - 1) - index];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = array[index + i + 1];
        }
        return tmp;
    }

    public boolean TASK3(int[] array){
        ArrayList<Integer> tmp = new ArrayList<>(array.length);
        for (int i = 0; i < array.length; i++) {
            tmp.add(array[i]);
        }
        return (tmp.contains(1) == tmp.contains(4));
    }
}