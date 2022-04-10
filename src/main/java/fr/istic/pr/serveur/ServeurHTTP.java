package fr.istic.pr.serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurHTTP {
    public static void main(String[] args) throws IOException {
        int listeningPort = 8080;
        ServerSocket socketServer = new ServerSocket(listeningPort);

        try {
            System.out.println("Waiting for clients");

            Socket socketClient = socketServer.accept();

            System.out.println("The client " + socketClient.getInetAddress() + " is connected");

            ClientHandler clientHandler = new HTTPHandler(socketClient);
            while (true) {
                clientHandler.handle();
            }
        } catch (IOException exception) {
            System.out.println("Error " + exception.getMessage());
        } finally {
            socketServer.close();
        }
    }
}
