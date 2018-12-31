package Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
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
        return getLine();
    }
    public String getLine() {
        try {
            this.read = new BufferedReader(new InputStreamReader(s.getInputStream()));
            while (true) {
                this.line = this.read.readLine(); // vs
                if (line != null) {
                    return this.line;
                }
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
