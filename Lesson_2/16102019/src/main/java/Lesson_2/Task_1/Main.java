package Lesson_2.Task_1;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        NewDB.connect();
        NewDB.createTableStudents();
        System.out.println();

        NewDB.clearTableStudents();
        NewDB.insertStudents();
        System.out.println();

        NewDB.disconnect();
    }
}

