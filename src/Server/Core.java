package Server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class Core implements Runnable {
    Socket conn;
    ExecutorService pool;
    ArrayList<Socket> connList;
    public Core(Socket conn, ExecutorService pool, ArrayList<Socket> connList) {
        this.conn = conn;
        this.pool = pool;
        this.connList = connList;
    }
    public void run() {
        Future<String> line = pool.submit(new ServerReadThread(conn));
        if (line.isDone()) {
            try {
                for(Socket sc:
                        this.connList) {
                    Thread write = new Thread(new ServerWriteThread(sc, line.get()));
                    write.run();
                    write.suspend();
                }
            } catch (java.lang.InterruptedException e) {

            } catch (java.util.concurrent.ExecutionException e) {

            }
        }
    }
}
