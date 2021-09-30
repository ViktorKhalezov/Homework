package ru.geekbrains.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

public class ClientHandler {


    private Server server;
    private DataInputStream in;
    private DataOutputStream out;
    private String name;
    private boolean isAuthentificated;


    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                try {
                    doAuthentication();
                    new Thread (() -> {
                        terminatingIfNoAuthentication(socket);
                    }).start();
                    listenMessages();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    server.unsubscribe(this);
                    server.broadcastMessage(String.format("User[%s] is out.", name));
                    closeConnection(socket);
                }
            })
                    .start();
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong during client establishing...", e);
        }
    }

    private void closeConnection(Socket socket) {

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
    }

    public String getName() {
        return name;
    }

    private void doAuthentication() throws IOException {
        sendMessage("Greeting you in the Outstanding Chat.");
        sendMessage("Please do authentication. Template is: -auth [login] [password]");



        while (true) {
            String maybeCredentials = in.readUTF();
            /** sample: -auth login1 password1 */


            if (maybeCredentials.startsWith("-auth")) {
                String[] credentials = maybeCredentials.split("\\s");

                Optional<AuthService.User> maybeUser = server.getAuthService()
                        .findUserByLoginAndPassword(credentials[1], credentials[2]);

                if (maybeUser.isPresent()) {
                    AuthService.User user = maybeUser.get();
                    if (server.isNotUserOccupied(user.getName())) {
                        name = user.getName();
                        sendMessage("AUTH OK.");
                        sendMessage("Welcome.");
                        server.broadcastMessage(String.format("User[%s] entered chat.", name));
                        server.subscribe(this);
                        isAuthentificated = true;
                        return;
                    } else {
                        sendMessage("Current user is already logged in");
                    }
                } else {
                    sendMessage("Invalid credentials.");
                }
            } else {
                sendMessage("Invalid auth operation");
            }
        }


    }

    public void sendMessage(String outboundMessage) {
        try {
            out.writeUTF(outboundMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listenMessages() throws IOException {
        while (true) {
            String inboundMessage = in.readUTF();
            if (inboundMessage.equals("-exit")) {
                break;
            }
            if(inboundMessage.startsWith("-w")){
                String[] messageParts = inboundMessage.split("\\s");
                String receiverName = messageParts[1];
                String privateMessage = inboundMessage.substring(receiverName.length() + 4);

                server.broadcastPrivateMessage(privateMessage, receiverName, this);
                continue;
            }
            if(inboundMessage.startsWith("-cname")){
                String oldName = this.getName();
                changeName(inboundMessage);
                String newName = this.getName();
                server.broadcastMessage("Пользователь " + oldName + " сменил имя. Его новое имя - " + newName);
                continue;
            }
            server.broadcastMessage(inboundMessage);
        }
    }


        public void terminatingIfNoAuthentication(Socket socket) {
                TimerTask terminateConnection = new TimerTask() {
                    @Override
                    public void run() {
                        sendMessage("Вы не авторизовались в течение 2 минут.\nВаше соединение прервано");
                        closeConnection(socket);
                    }
                };

            Timer terminatingTimer = new Timer();
                if (isAuthentificated == false) {
                    terminatingTimer.schedule(terminateConnection, 20000);
                }
        }

        public void changeName (String inboundMessage) {
            Connection connection = DatabaseConnection.getConnection();
            String newName = inboundMessage.split("\\s")[1];

            try {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM users;");
                while(rs.next()){
                    if(rs.getString("name").equals(newName)){
                        sendMessage("Желаемое имя занято. Повторите запрос с другим именем.");
                        DatabaseConnection.closeConnection(connection);
                        return;
                    }
                }
                String updateQuery = String.format("UPDATE users SET name = '%s' WHERE name = '%s';", newName, name);
                statement.executeUpdate(updateQuery);
                name = newName;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DatabaseConnection.closeConnection(connection);
            }

        }


}
