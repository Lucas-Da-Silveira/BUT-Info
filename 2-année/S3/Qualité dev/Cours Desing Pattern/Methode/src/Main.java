public class Main {

    public static void main(String[] args) {
        File<Carte> q1 = new File<>();
        File<Carte> q2 = new File<>();
        Joueur j1 = new Joueur("Thomas",q1);
        Joueur j2 = new Joueur("Clement",q2);
        Joueur[] Joueurs = new Joueur[2];
        Joueurs[0] = j1;
        Joueurs[1] = j2;
        Paquet52 paquet = new Paquet52();
        BatailleInversee b = new BatailleInversee(paquet,Joueurs);
        b.jouerPartie();
    }
}
