package ru.geekbrains.chat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;

public class ClientHandler {
    private Server server;
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private ArrayList<String> blackList;
    private String nick;


    public String getNick() {
        return nick;
    }

    public boolean checkBlackList(String nick) {
        return blackList.contains(nick);
    }

    public ClientHandler(Server server, Socket socket) {
        try {
            this.socket = socket;
            this.server = server;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.blackList = new ArrayList<>();
            Thread t1 = new Thread(() -> {
                try {
                    while (true) {
                        String str = in.readUTF();
                        if (str.startsWith("/auth")) {
                            String[] tokens = str.split(" ");
                            server.getLogger().log(Level.INFO, tokens[1] + ": попытка подключения.");
                            String newNick = DataBase.getNickByLoginAndPass(tokens[1], tokens[2]);

                            if (newNick != null) {
                                if (!server.isNickBusy(newNick)) {
                                    sendMsg("/authok");

                                    nick = newNick;
                                    server.subscribe(this);
                                    DataBase.getHistory(this);
                                    server.getLogger().log(Level.INFO, tokens[1] + ": клиент подключен.");
                                    break;
                                } else {
                                    server.getLogger().log(Level.INFO, tokens[1] + ": учетная запись  уже используется.");
                                    sendMsg("Учетная запись уже используется");
                                }
                            } else {
                                server.getLogger().log(Level.INFO, tokens[1] + ": неверный логин/пароль");
                                sendMsg("Неверный логин/пароль");
                            }
                        }
                    }
                    while (true) {
                        String str = in.readUTF();
                        if (str.startsWith("/")) {
                            if (str.equals("/end")) {
                                out.writeUTF("/serverclosed");
                                break;
                            }

                            if (str.startsWith("/w ")) {
                                String[] tokens = str.split(" ", 3);
                                server.sendPersonalMsg(this, tokens[1], tokens[2]);
                                // String[] tokens = str.split(" ");
                            }

                            if (str.startsWith("/blacklist ")) {
                                String[] tokens = str.split(" ");
                                blackList.add(tokens[1]);
                                sendMsg("Вы добавили пользователя " + tokens[1] + " в черный список");
                            }

                        } else {
                            server.broadcastMsg(this, str);
                        }
                        System.out.println("Client: " + str);
                    }
                } catch (IOException e) {
                    // e.printStackTrace();
                } catch (SQLException e) {
                    // e.printStackTrace();
                } catch (ArrayIndexOutOfBoundsException e) {
                    server.getLogger().log(Level.INFO, "Поля для входа не заполнены.");
                } finally {
                    closeConnection();
                }
            });
            t1.setDaemon(true);
            t1.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.unsubscribe(this);

        if (nick != null){
            try {
                server.getLogger().log(Level.INFO, DataBase.getLogin(nick) + ": клиент отключился.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else server.getLogger().log(Level.INFO, "Клиент отключился.");
    }
}