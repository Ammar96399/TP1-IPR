package fr.istic.pr.ping;

import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Date;
import java.sql.Time;
import java.util.Arrays;

public class HttpsPing {
    public static class PingInfo {
        //Le temps de réponse :
        long time;
        //Le code de réponse :
        int code;

        @Override
        public String toString() {
            return String.format("[code %d -- %d ms]", code, time);
        }
    }

    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
        System.out.println(ping("www.google.fr", 80));

    }

    public static PingInfo ping(String host, int port) throws UnknownHostException, IOException {
        PingInfo info = new PingInfo();

        long time = System.currentTimeMillis();

        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        Socket socket = factory.createSocket("www.google.com", 443);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        // UTILISER PrintWriter et BufferedReader
        out.println("GET / HTTP/1.1");
        out.println("Host: example.com" );
        // Penser à préciser l'Host dans l'en-tête

        // L'en-tête doit également contenir :
        out.println("Connection: close"); //demande au site de fermer la connexion après la réponse.
        // et se terminier par l'envoie d'une ligne vide sans espace
        out.println();
        /// Envoyer l'entête
        out.flush();
        // LECTURE DE LA REPONSE
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //info.code = utiliser split sur la première ligne pour parser le code
        info.code=Integer.parseInt(in.readLine().split(" ")[1]);

        String code;
        //AFFICHAGE DE LA PAGE
        while ((code = in.readLine()) != null) {
            System.out.println(code);
        }
        info.time = System.currentTimeMillis() - time;
        return info;
    }
}
