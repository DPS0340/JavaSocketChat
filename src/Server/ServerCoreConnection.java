package Server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerCoreConnection implements Runnable {
    ArrayList<Socket> connList;
    public ServerCoreConnection(ArrayList<Socket> connList) {
        this.connList = connList;
    }
    @Override
    public void run() {
        ExecutorService execService = Executors.newCachedThreadPool();
            for (Socket conn :
                    this.connList) {
                Thread th = new Thread(new Core(conn, connList, execService));
                th.run();
            }
    }
}
