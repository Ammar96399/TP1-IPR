package fr.istic.pr.echo;

import java.io.IOException;

public interface ClientHandler {
    /** La méthode handle traite le client **/
    public void handle() throws IOException;
}