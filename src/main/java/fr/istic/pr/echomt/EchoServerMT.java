package fr.istic.pr.echomt;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EchoServerMT {
    public static void main(String[] args) throws IOException {
        int listeningPort = 8080;
        ServerSocket serverSocket = new ServerSocket(listeningPort);

        try {
            Executor service = Executors.newFixedThreadPool(4);
            while (true) {
                System.out.println("Waiting for clients");
                Socket clientSocket = serverSocket.accept();
                System.out.println("The client " + clientSocket.getInetAddress() + " is connected");

                service.execute(new ClientHandlerMT(clientSocket));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            serverSocket.close();
        }
    }
}
