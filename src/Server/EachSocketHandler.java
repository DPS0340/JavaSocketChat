package Server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.*;

public class EachSocketHandler implements Runnable {
    ArrayList<String> lines = new ArrayList<String>();
    ArrayList<Socket> connList;
    public EachSocketHandler(ArrayList<Socket> connList) {
        this.connList = connList;
    }

    @Override
    public void run() {
        ExecutorService pool = Executors.newFixedThreadPool(this.connList.size());
        for (Socket conn:
             this.connList) {
            Thread core = new Thread(new Core(conn, pool, this.connList));
            core.run();
        }
    }
}
