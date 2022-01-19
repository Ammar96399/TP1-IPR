
package fr.istic.pr.echo;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class ClientHandlerBytes implements ClientHandler {

    private Socket socket;
    //Lecture du message dans un buffer de bytes
    private byte[] buffer;

    public ClientHandlerBytes(Socket socket){
        this.socket = socket;
    }

    public void handle() throws IOException {
        // Initialisation du buffer
        buffer = new byte[8];

        // Récupération du message envoyer par le client
        InputStream input = socket.getInputStream();
        input.read(buffer);
        String inputToString = new String(buffer);
        System.out.println("Message sent by the client: " + inputToString);

        OutputStream output = socket.getOutputStream();
        output.write(buffer);
        // Message en bytes ==> string
        /*String inputToString = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8)).lines().collect(Collectors.joining("\n"));
        System.out.println(inputToString);*/
    }
}
