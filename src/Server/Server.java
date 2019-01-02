package Server;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) {
        Thread currentThread = new Thread();
        ArrayList<Socket> connList = new ArrayList<Socket>();
        try {
            ServerSocket s = new ServerSocket(8888);
            while(true) {
                System.out.println("서버에서 연결 대기 중...");
                Socket toClient = s.accept();
                connList.add(toClient);
                System.out.println(connList);
                System.out.println(toClient.getInetAddress());
                try {
                    currentThread.suspend();
                } finally {
                    currentThread = new Thread(new ServerCoreConnection(connList));
                    currentThread.start();
                }
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } catch (java.lang.NullPointerException e) {
            e.printStackTrace();
        }
    }
}