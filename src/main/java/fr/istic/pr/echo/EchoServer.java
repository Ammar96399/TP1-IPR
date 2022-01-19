package fr.istic.pr.echo;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public static void main(String[] args) throws IOException {

        // Création de la socket serveur sur le port 8080
        int listeningPort = 8080;
        ServerSocket socketServer = new ServerSocket(listeningPort);

        try {
            // Attente de connexion d'un client (pas de timeout ici)
            System.out.println("Waiting for clients");
            Socket socketClient = socketServer.accept();
            System.out.println("The client " + socketClient.getInetAddress() + " is connected");

            // Création d'un ClientHandler
            ClientHandler clientHandler = new ClientHandlerBytes(socketClient);

            while (true) {
                // Appel de la fonction handle()
                clientHandler.handle();
            }
        } catch (IOException exception){
            System.out.println("Error " + exception.getMessage());
        } finally {
            socketServer.close();
        }
        //Attente sur le port 8080

        /* Pour chaque client : 
            1. accepter la connexion.
            2. créer un ClientHandler
            3. appeler la méthode handleBytes() sur le handler
        */

    }
}