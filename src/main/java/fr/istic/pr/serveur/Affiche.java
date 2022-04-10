package fr.istic.pr.serveur;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Affiche {
    public static void main(String[] args) throws IOException {
        String path = "test.html";
        File f = new File("./www/" + path);
        if (f.exists() && !f.isDirectory()) {
            System.out.println(f.getAbsolutePath());
            System.out.println(f.exists());
            BufferedReader fin = new BufferedReader(new FileReader(f));
            String line = "";
            while ((line = fin.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}