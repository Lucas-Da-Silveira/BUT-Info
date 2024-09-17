import java.io.*;
import java.net.*;

class EchoServer {

    public static void main(String[] args) {

        BufferedReader br = null; // pour lire du texte sur la socket
        PrintStream ps = null; // pour envoyer du texte sur la socket
        String line = null; // la ligne reçu/envoyée
        ServerSocket conn = null;
        Socket sock = null;
        int port = -1;

        if (args.length != 1) {
            System.out.println("usage: Server port");
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
                br = new BufferedReader(new InputStreamReader(sock.getInputStream())); // creation flux lecture lignes de textes
                ps = new PrintStream(sock.getOutputStream()); // création flux écriture lignes de texte

                while ((line = br.readLine()) != null && !line.isEmpty()) {
                    System.out.println("Le client me dit : " + line);
                    ps.println(line);
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