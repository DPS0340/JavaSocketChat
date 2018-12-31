package Server;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args) {
        int port = 8888;
        try {
            ServerSocket socket = new ServerSocket(port);
            Thread th = new Thread(new ServerListenThread(socket));
            th.run();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}