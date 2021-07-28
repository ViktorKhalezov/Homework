package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    private ServerSocket socket;
    private Socket client;
    private DataInputStream in;
    private DataOutputStream out;
    private Scanner scanner;


    public Server() {
        init();
        communicate();

    }

    public void init() {
        try {
            socket = new ServerSocket(8899);
            System.out.println("Ожидается подключение...");
            client = socket.accept();
            System.out.println("Клиент подключился");
            System.out.println();
        } catch (IOException e) {
            System.out.println("Bad status");
            e.printStackTrace();
        }
    }

    public void communicate() {

        try {
            in = new DataInputStream(client.getInputStream());
            out = new DataOutputStream(client.getOutputStream());
            scanner = new Scanner(System.in);


            new Thread( () -> {
                try {
                    while (true) {
                       String inMessage = in.readUTF();
                        if (inMessage.equals("exit")) {
                            out.writeUTF("До свидания!");
                            System.out.println("Клиент прервал соединение");
                            System.out.println("Нажмите Enter для завершения сеанса");
                            closeConnection();

                        }
                        System.out.println("Клиент:");
                        System.out.println(inMessage);
                        System.out.println();


                    }
                }catch(IOException e){
                    e.printStackTrace();
                }
            }).start();

            new Thread( () -> {
                try {
                while(true) {

                    System.out.println("Вы:");
                    String outMessage = scanner.nextLine();
                    System.out.println();
                    if (outMessage.equals("exit")) {
                        System.out.println("Соединение прекращено");
                        out.writeUTF("До свидания!");
                        out.writeUTF("exit");
                        closeConnection();
                    }
                    out.writeUTF(outMessage);
                }
                }catch(IOException e){
                    e.printStackTrace();
            }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void closeConnection(){

        try {
            in.close();
        } catch(IOException e){
            e.printStackTrace();
        }
        try {
            out.close();
        } catch(IOException e){
            e.printStackTrace();
        }

        try {
            socket.close();
        } catch(IOException e){
            e.printStackTrace();
        }
        scanner.close();
        System.exit(0);
    }


}
