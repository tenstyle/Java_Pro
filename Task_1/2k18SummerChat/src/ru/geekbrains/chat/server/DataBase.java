package ru.geekbrains.chat.server;

import java.sql.*;

public class DataBase {

    private static Connection connection;
    private static Statement stmt;

    public static void connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:users.db");
            stmt = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static String getNickByLoginAndPass(String login, String pass) {

        String sql = String.format("select nickname FROM userTable where" +
                " login = '%s' and password = '%s'", login, pass);
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return rs.getString(1);
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

    public static void addMessageToBase(String nick, String msg) {
        ResultSet resultSet;
        String login;
        String sql = String.format("SELECT login FROM userTable WHERE (nickName = '%s');", nick);
        try {
            resultSet = stmt.executeQuery(sql);
            login = resultSet.getString(1);
            sql = String.format("INSERT INTO History (login, message, nickName) VALUES ('%s', '%s', '%s');", login, msg, nick);
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static String getLogin(String nick) throws SQLException {
        ResultSet resultSet;
        String login;

        String sql = String.format("SELECT login FROM userTable WHERE (nickName = '%s');", nick);
        resultSet = stmt.executeQuery(sql);
        login = resultSet.getString(1);
        return login;
    }

    public static void getHistory(ClientHandler client) throws SQLException {
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM History");
        while (resultSet.next()) {
            String msg = resultSet.getString(3) + " " + resultSet.getString(2);
            client.sendMsg(msg);
        }
    }
}