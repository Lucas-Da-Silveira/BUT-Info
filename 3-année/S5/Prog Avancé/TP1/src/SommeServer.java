import java.io.*;
import java.net.*;

public class SommeServer {

    public static void main(String[] args) {

        BufferedReader br = null; // pour lire du texte sur la socket
        PrintStream ps = null; // pour envoyer du texte sur la socket
        String line = null; // la ligne reçue/envoyée
        ServerSocket conn = null;
        Socket sock = null;
        int port = -1;

        if (args.length != 1) {
            System.out.println("usage: SommeServer port");
            System.exit(1);
        }

        try {
            port = Integer.parseInt(args[0]); // récupération du port sous forme int
            conn = new ServerSocket(port); // création socket serveur
        } catch (IOException e) {
            System.out.println("problème création socket serveur : " + e.getMessage());
            System.exit(1);
        }

        while (true) {
            try {
                sock = conn.accept(); // attente connexion client
                br = new BufferedReader(new InputStreamReader(sock.getInputStream())); // création flux lecture lignes de textes
                ps = new PrintStream(sock.getOutputStream()); // création flux écriture lignes de texte

                while ((line = br.readLine()) != null && !line.isEmpty()) {
                    try {
                        int count = Integer.parseInt(line.trim()); // lire le nombre d'entiers
                        int sum = 0;
                        for (int i = 0; i < count; i++) {
                            line = br.readLine();
                            sum += Integer.parseInt(line.trim()); // conversion et somme des entiers
                        }
                        ps.println(sum); // envoi du résultat au client
                    } catch (NumberFormatException e) {
                        ps.println("REQ_ERR"); // envoi du message d'erreur en cas de requête malformée
                    }
                }

                br.close();
                ps.close();
                sock.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}