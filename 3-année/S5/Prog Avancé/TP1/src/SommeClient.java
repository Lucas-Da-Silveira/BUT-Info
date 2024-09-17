import java.io.*;
import java.net.*;

class SommeClient {

    public static void main(String[] args) {

        BufferedReader br = null; // pour lire du texte sur la socket
        PrintStream ps = null; // pour écrire du texte sur la socket
        BufferedReader userInput = null; // pour lire le texte de l'utilisateur
        String line = null;
        Socket sock = null;
        int port = -1;

        if (args.length != 2) {
            System.out.println("usage: SommeClient ip_server port");
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
                System.out.print("Entrez une série de nombres entiers séparés par des virgules: ");
                line = userInput.readLine(); // lecture du message de l'utilisateur

                if (line == null || line.isEmpty()) {
                    break; // sortir de la boucle si la ligne est vide ou null (Ctrl+D)
                }

                String[] numbers = line.split(",");
                int[] validNumbers = new int[numbers.length];
                int count = 0;

                for (String number : numbers) {
                    try {
                        validNumbers[count] = Integer.parseInt(number.trim());
                        count++;
                    } catch (NumberFormatException e) {
                        System.out.println("Requête malformée");
                        count = 0;
                        break;
                    }
                }

                if (count > 0) {
                    ps.println(count); // envoyer le nombre d'entiers
                    for (int i = 0; i < count; i++) {
                        ps.println(validNumbers[i]); // envoyer chaque entier
                    }
                    String response = br.readLine(); // lecture réponse serveur
                    System.out.println("La somme est : " + response);
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