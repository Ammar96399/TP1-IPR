package fr.istic.pr.echomt;

import java.io.IOException;

public interface ClientHandler {
    /** La méthode handle traite le client **/
    public void handle() throws IOException;
}