import java.util.ArrayList;

public class SpiralMain {
    public static void main(String[] args) {
        //Зададим размер массива переменными c - столбец и l - строка
        int c = 5;
        int l = 5;
        //стартовая цифра:
        int s = 1;

        int[][] arr = new int[c][l];

        for (int i = 0; i < l; i++) {
            arr[0][i] = s;
            s++;
        }

        for (int j = 1; j < c; j++) {
            arr[j][l - 1] = s;
            s++;
        }

        for (int i = l - 2; i >= 0; i--) {
            arr[c - 1][i] = s;
            s++;
        }

        for (int j = c - 2; j > 0; j--) {
            arr[j][0] = s;
            s++;
        }

        int c1 = 1;
        int l1 = 1;

        while (s < c * l) {
            while (arr[c1][l1 + 1] == 0) {
                arr[c1][l1] = s;
                s++;
                l1++;
            }
            while (arr[c1 + 1][l1] == 0) {
                arr[c1][l1] = s;
                s++;
                c1++;
            }
            while (arr[c1][l1 - 1] == 0) {
                arr[c1][l1] = s;
                s++;
                l1--;
            }
            while (arr[c1-1][l1] == 0) {
                arr[c1][l1] = s;
                s++;
                c1--;
            }
        }
        for (int j = 0; j < c; j++) {
            for (int i = 0; i < l; i++) {
                if (arr[j][i] == 0) {
                    arr[j][i] = s;
                }
            }
        }

        for (int j = 0; j < c; j++) {
            for (int i = 0; i < l; i++) {
                if (arr[j][i] < 10) {
                    System.out.print(arr[j][i] + "\t");
                } else {
                    System.out.print(arr[j][i] + "\t");
                }
            }
            System.out.println("");
        }
    }
}
