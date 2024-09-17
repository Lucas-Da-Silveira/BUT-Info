import java.io.*;
import java.net.*;

class EchoClient {

    public static void main(String[] args) {

        BufferedReader br = null; // pour lire du texte sur la socket
        PrintStream ps = null; // pour écrire du texte sur la socket
        BufferedReader userInput = null; // pour lire le texte de l'utilisateur
        String line = null;
        Socket sock = null;
        int port = -1;

        if (args.length != 2) {
            System.out.println("usage: EchoClient ip_server port");
            System.exit(1);
        }

        try {
            port = Integer.parseInt(args[1]); // récupération du port sous forme int
            sock = new Socket(args[0], port); // création socket client et connexion au serveur donné en args[0]
        } catch (IOException e) {
            System.out.println("problème de connexion au serveur : " + e.getMessage());
            System.exit(1);
        }

        try {
            br = new BufferedReader(new InputStreamReader(sock.getInputStream())); // création flux lecture lignes de texte
            ps = new PrintStream(sock.getOutputStream()); // création flux écriture lignes de texte
            userInput = new BufferedReader(new InputStreamReader(System.in)); // création flux lecture de l'utilisateur

            while (true) {
                System.out.print("Entrez un message: ");
                line = userInput.readLine();

                if (line == null || line.isEmpty()) {
                    break;
                }

                ps.println(line);
                String response = br.readLine();
                System.out.println("Le serveur me répond : " + response);
            }

            br.close();
            ps.close();
            sock.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}