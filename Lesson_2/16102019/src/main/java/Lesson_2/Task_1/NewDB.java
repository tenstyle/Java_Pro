package Lesson_2.Task_1;

import java.sql.*;

public class NewDB {

    static Connection connection;
    static Statement statement;
    static ResultSet rs;
    static PreparedStatement preparedStatement = null;
    static String[] ss = null;

    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:main.db");
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTableStudents(){
        String createTable =" CREATE TABLE IF NOT EXISTS students (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "score TEXT)";

        try {
            statement.executeUpdate(createTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void clearTableStudents(){
        String clearTable = "DELETE from students";
        try {
            int clear = statement.executeUpdate(clearTable);
            System.out.println("Удалено строк: " + clear);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertStudents() throws SQLException {
        connection.setAutoCommit(false);

        for (int i = 0; i < 1000; i++) {
            String res = "Bob" + i;

            statement.addBatch(String.format("INSERT INTO students (name, score)\n" +
                    "VALUES ('%s', %d)", res, i));

            statement.executeUpdate(String.format("INSERT INTO students (name, score)\n" +
                    "VALUES ('%s', %d)", res, i));
        }


        statement.executeBatch();

        connection.setAutoCommit(true);
    }

    public static void disconnect()  {

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}