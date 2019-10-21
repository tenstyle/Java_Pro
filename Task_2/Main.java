package Task_2;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String[] array = {"a", "b", "c", "d", "e", "f"};
        transformToArrayList(array);
    }

    private static <T> void transformToArrayList(T[] array) {
        List transformedArray = new ArrayList();
        for (T element:array) {
            transformedArray.add(element);
            System.out.println(element);
        }
    }
}
