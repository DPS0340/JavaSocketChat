package Client;
import java.net.Socket;


public class Client {
    public static void main(String[] args) {
        int port = 8888;
        try {
            Socket socket = new Socket("127.0.0.1", port);
            Thread ClientIO = new Thread(new ClientIOThread(socket));
            ClientIO.run();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
