package fr.istic.pr.echomt;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientHandlerMT implements ClientHandler, Runnable {
    private Socket socket;

    public ClientHandlerMT(Socket socket) { this.socket = socket; }
    @Override
    public void handle() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), StandardCharsets.UTF_8));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream(), StandardCharsets.UTF_8));
        String tmp;
        while ((tmp = br.readLine()) != null) {
            pw.println(tmp);
            pw.flush();
        }
        socket.close();
    }

    @Override
    public void run() {
        try {
            handle();
            socket.close();
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage() + " with socket " + socket.getInetAddress());
        }
    }
}
