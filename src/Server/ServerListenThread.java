package Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerListenThread implements Runnable {
    ServerSocket s;
    public ServerListenThread(ServerSocket s) {
        this.s = s;
    }
    @Override
    public void run() {
        try {
            ArrayList<Socket> connList = new ArrayList<Socket>();
            while(true) {
                System.out.println("서버에서 연결 대기 중...");
                Socket toClient = this.s.accept();
                connList.add(toClient);
                System.out.println(toClient.getInetAddress());
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } catch (java.lang.NullPointerException e) {
        }
    }
}
