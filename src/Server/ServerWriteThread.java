package Server;

import java.io.*;
import java.net.Socket;

public class ServerWriteThread implements Runnable {
    Socket s;
    PrintWriter write;
    String line;
    public ServerWriteThread(Socket s, String line) {
        this.s = s;
    }
    @Override
    public void run() {
        writeline(line);
    }
    public void writeline(String line) {
        try {
            this.write = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())));
            this.write.println(line);
            this.write.flush();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}