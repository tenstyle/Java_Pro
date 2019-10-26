package Task_3;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    private static final int pageLettersNumber = 1800;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        File book = new File("task3-book.txt");
        long pageCount = book.length() / pageLettersNumber;

        RandomAccessFile readBook = new RandomAccessFile(book, "r");
        while (true) {
            System.out.println("Общее количество страниц: " + pageCount + "\nВведите номер страницы:");
            int userPage = sc.nextInt();
            if (userPage < 1 || userPage > pageCount) {
                System.out.println("Такой страницы не существует. Введите корректную страницу");
                continue;
            }
            int position = pageLettersNumber * (userPage - 1);
            readBook.seek(position);
            for (int i = 0; i < pageLettersNumber / 4; i++) {
                String utf8 = new String(readBook.readLine().getBytes("ISO-8859-1"), "UTF-8");
                System.out.println(utf8);
            }
        }
    }
}