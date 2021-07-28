package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;



public class Client {

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Scanner scanner;


    public Client() {
        init();
        communicate();
    }

    public void init() {
        try {
            socket = new Socket("localhost", 8899);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void communicate() {

        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            scanner = new Scanner(System.in);



        new Thread(() -> {
            try {
                while (true) {
                   String inMessage = in.readUTF();
                    if (inMessage.equals("exit")) {
                        System.out.println("Сервер прервал соединение");
                        System.out.println("Нажмите Enter для завершения сеанса");
                        closeConnection();
                    }
                    System.out.println("Сервер:");
                    System.out.println(inMessage);
                    System.out.println();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
            }).start();

        new Thread(() -> {
            try {
                while (true) {

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


        } catch (IOException e){
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
