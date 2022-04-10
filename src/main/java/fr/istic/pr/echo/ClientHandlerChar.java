package fr.istic.pr.echo;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class ClientHandlerChar implements ClientHandler{

    private Socket socket;

    public ClientHandlerChar(Socket socket) { this.socket = socket; }
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
}
