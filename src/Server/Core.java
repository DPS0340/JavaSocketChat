package Server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class Core implements Runnable {
    ArrayList<Socket> connList;
    Socket conn;
    ExecutorService execService;
    public Core(Socket conn, ArrayList<Socket> connList, ExecutorService execService) {
        this.conn = conn;
        this.execService = execService;
        this.connList = connList;
    }

    @Override
    public void run() {
        try {
            while(true) {
                Future<String> resultline = execService.submit(new ServerReadThread(conn));
                String line = resultline.get();
                System.out.println(line);
                for (Socket semiconn :
                        this.connList) {
                    Thread write = new Thread(new ServerWriteThread(semiconn, line));
                    write.run();
                }
            }
        } catch (java.lang.InterruptedException e) {
            e.getStackTrace();
        } catch (java.util.concurrent.ExecutionException e) {
            e.getStackTrace();
        }
    }
}
