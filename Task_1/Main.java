package Task_1;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[] array = {"a", "b", "c", "d", "e"};
        System.out.println("Массив до перемены: " + Arrays.toString(array));
        try {
            elementsReplace(array, 1, 3);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Введенные значение выходят за пределы массива...");
        }
    }

    private static <T> void elementsReplace(T[] array, int firstEl, int secondEl) {
        T temp = array[secondEl];
        array[secondEl] = array[firstEl];
        array[firstEl] = temp;
            System.out.println("Массив после перемены: " + Arrays.toString(array));
    }
}
