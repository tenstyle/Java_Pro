package ru.geekbrains.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;
import java.util.logging.*;

public class Server {

    private Vector<ClientHandler> clients;
    private final Logger logger = Logger.getLogger(ru.geekbrains.chat.server.Server.class.getName());
    private Handler fileHandler;

    {
        try {
            fileHandler = new FileHandler("logs.log", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Server() throws SQLException{
        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;

        // Логирование
        // logger.setLevel(Level.ALL);
        // logger.getHandlers()[0].setLevel(Level.ALL);
        logger.addHandler(fileHandler);
        logger.getHandlers()[0].setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                return new Date().toString() + "\t" + record.getLevel() + "\t" + record.getMessage() + "\n";
            }
        });


        try {
            DataBase.connect();
            server = new ServerSocket(8189);
            logger.log(Level.SEVERE, "Сервер запущен.");
            while (true) {
                socket = server.accept();
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                // e.printStackTrace();
            }
            try {
                server.close();

            } catch (IOException e) {
                // e.printStackTrace();
            }
            DataBase.disconnect();
            logger.log(Level.SEVERE, "Сервер выключен.");
        }
    }

    public Logger getLogger() {
        return logger;
    }

    public void broadcastMsg(ClientHandler from, String msg) {
        for (ClientHandler o : clients) {
            if (!o.checkBlackList(from.getNick())) {
                o.sendMsg(from.getNick() + ": " + msg);
            }
        }
        DataBase.addMessageToBase(from.getNick(), msg);
    }

    public boolean isNickBusy(String nick) {
        for (ClientHandler o : clients) {
            if (o.getNick().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    public void broadcastClientList() {
        StringBuilder sb = new StringBuilder();
        sb.append("/clientList ");
        for (ClientHandler o : clients) {
            sb.append(o.getNick() + " ");
        }
        String out = sb.toString();

        for (ClientHandler o : clients) {
            o.sendMsg(out);
        }
    }

    public void sendPersonalMsg(ClientHandler from, String nickTo, String msg) {
        for (ClientHandler o : clients) {
            if (o.getNick().equals(nickTo)) {
                o.sendMsg("from " + from.getNick() + ": " + msg);
                from.sendMsg("to " + nickTo + ": " + msg);
                return;
            }
        }
        from.sendMsg("Клиент с ником " + nickTo + ": " + msg);
    }

    public void subscribe(ClientHandler client) {
        clients.add(client);
        broadcastClientList();
    }

    public void unsubscribe(ClientHandler client) {
        clients.remove(client);
        broadcastClientList();
    }
}