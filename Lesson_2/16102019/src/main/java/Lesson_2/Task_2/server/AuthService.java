package Lesson_2.Task_2.server;

import java.sql.*;
import java.util.ArrayList;

public class AuthService {

    private static Connection connection;
    private static Statement stmt;

    public static void connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:chatUsers.db");
            stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE if not exists history (id INTEGER PRIMARY KEY AUTOINCREMENT, message STRING);");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static String getNickByLoginAndPass(String login, String pass) {
        try {
            ResultSet rs = stmt.executeQuery("SELECT nickname, password FROM main WHERE login = '" + login + "'");
            int myHash = pass.hashCode();
            if (rs.next()) {
                String nick = rs.getString(1);
                int dbHash = rs.getInt(2);
                if (myHash == dbHash) {
                    return nick;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveMsg(String msg){
        String sql = String.format("INSERT INTO history (message) VALUES ('%s');", msg);
        try {
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getLastMsg(){
        String sql = ("SELECT message FROM history;");
        ArrayList<String> msg = new ArrayList<>();

        try {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                msg.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return msg;
    }
}