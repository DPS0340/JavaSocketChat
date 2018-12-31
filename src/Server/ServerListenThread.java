package Server;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerListenThread implements Runnable {
    ServerSocket s;
    public ServerListenThread(ServerSocket s) {
        this.s = s;
    }
    @Override
    public void run() {
        try {
            while(true) {
                System.out.println("서버에서 연결 대기 중...");
                Socket toClient = this.s.accept();
                System.out.println(toClient.getInetAddress());
                Thread ServerIO = new Thread(new ServerReadWriteThread(toClient));
                ServerIO.run();
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } catch (java.lang.NullPointerException e) {
        }
    }
}
