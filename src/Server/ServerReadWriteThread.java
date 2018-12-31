package Server;

import java.io.*;
import java.net.Socket;

public class ServerReadWriteThread implements Runnable {
    Socket s;
    BufferedReader read;
    PrintWriter write;
    String line;
    public ServerReadWriteThread(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            this.read = new BufferedReader(new InputStreamReader(s.getInputStream()));
            this.write = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())));
            this.write.println("연결됨!");
            this.write.flush();
            while (true) {
                this.line = this.read.readLine();
                if (line != null) {
                    System.out.println(this.line);
                    this.write.println(this.line);
                    this.write.flush();
                }
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}