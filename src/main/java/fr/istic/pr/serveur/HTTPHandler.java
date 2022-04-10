package fr.istic.pr.serveur;

/*imports*/

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class HTTPHandler implements ClientHandler, Runnable {

    private Socket socket;

    public HTTPHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void handle() throws IOException {
        // Crée les print writer et buffered reader
        BufferedReader br = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), StandardCharsets.UTF_8));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream(), StandardCharsets.UTF_8));
        // lit l'entête de la requête
        String firstLine = br.readLine();
        String method = firstLine.split(" ")[0];
        String page = firstLine.split(" /")[1].split(" ")[0];
        System.out.println(page);
        // Appelle doGet si la méthode est une méthode GET.
        if (Objects.equals(method, "GET")) {
            doGet(page, pw);
        } else {
            doError(pw);
        }
    }

    public void doGet(String pagepath, PrintWriter out) {
        //Vérifie que le fichier existe
        File f = new File("./www/" + pagepath);
        // si le fichier existe :
        try {
            if (f.exists() && !f.isDirectory()) {
                //Ecrit l'en-tête de réponse (Code, Content-type, Connexion, ...)
                out.println("HTTP/1.1 200 OK\n" +
                        "Content-Type: text/html; charset=UTF-8\n" +
                        "Connexion: close\n"
                );
                //Ecrit le contenu du fichier si il existe
                BufferedReader code = new BufferedReader(new FileReader(f));
                String line = "";
                while ((line = code.readLine()) != null) {
                    out.println(line);
                }
                out.flush();
            }
            // sinon
            else {
                // appelle la méthode send404.
                send404(out);
                out.flush();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void send404(PrintWriter out) throws IOException {
        //Envoie une réponse 404 si le fichier n'existe pas.
        out.println("HTTP/1.1 200 OK\n" +
                "Content-Type: text/html; charset=UTF-8\n" +
                "Connexion: close\n"
        );
        //Ecrit le contenu du fichier si il existe
        BufferedReader code = new BufferedReader(new FileReader("./www/404/html"));
        String line = "";
        while ((line = code.readLine()) != null) {
            out.println(line);
        }
        out.flush();
    }

    public void doError(PrintWriter out) {
        //Envoie une réponse avec le code 405 : Method Not Allowed
        out.println("Error 405: Method Not Allowed");
        out.flush();
    }

    @Override
    public void run() {

    }
}