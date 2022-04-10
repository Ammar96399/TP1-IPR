
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

        // Renvoie du message vers le client
        OutputStream output = socket.getOutputStream();
        output.write(buffer);

        socket.close();
    }
}
