package Server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Core implements Runnable {
    ArrayList<Socket> connList;
    public Core(ArrayList<Socket> connList) {
        this.connList = connList;
    }
    @Override
    public void run() {
        ExecutorService execService = Executors.newCachedThreadPool();
            for (Socket conn :
                    this.connList) {
                Thread th = new Thread(new SemiCore(conn, connList, execService));
                th.run();
            }
    }
}
