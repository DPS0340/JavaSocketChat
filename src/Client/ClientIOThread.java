package Client;

import java.io.*;

import java.net.Socket;

import java.util.Scanner;

public class ClientIOThread implements Runnable {
    Socket s;
    Scanner sc = new Scanner(System.in);
    BufferedReader read;
    PrintWriter write;
    String readline;
    String writeline;
    public ClientIOThread(Socket s) {
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
                this.readline = this.read.readLine();
                if (readline != null) {
                    System.out.println(this.writeline);
                }
                if (sc.hasNextLine()) {
                    this.writeline = sc.nextLine();
                    if (this.writeline.equals("!quit")) {
                        this.write.println(this.writeline);
                        this.write.flush();
                        s.close();
                    }
                    this.write.println(this.writeline);
                    this.write.flush();
                }
            }
        }
        catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
