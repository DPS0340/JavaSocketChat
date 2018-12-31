package Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.Callable;

public class ServerReadThread implements Callable<String> {
    Socket s;
    BufferedReader read;
    String line;
    public ServerReadThread(Socket s) {
        this.s = s;
    }
    @Override
    public String call() {
        try {
            this.read = new BufferedReader(new InputStreamReader(s.getInputStream()));
            this.line = this.read.readLine();
            if (this.line != null) {
                if (line.equals("!quit")) {
                    return "";
                }
                return this.line;
            } else {
                return "";
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
