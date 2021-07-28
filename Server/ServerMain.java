package Server;

public class ServerMain {

    public static void main(String[] args) {

        new Thread(() -> new Server()).start();
    }

}
