import java.util.*;

class Club {
    private String nom;
    private ArrayList<Footballeur> joueurs;
    private Footballeur[] equipe;

    public Club(String nom) {
        this.nom = nom;
        joueurs = new ArrayList<Footballeur>();
    }

    public void init() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nom du club : ");
        nom = sc.nextLine();
        System.out.print("Nombre de joueurs : ");
        int nbJoueurs = sc.nextInt();
        sc.nextLine(); 
        for (int i = 0; i < nbJoueurs; i++) {
            Footballeur joueur = new Footballeur("", 0, this);
            joueur.init();
            joueurs.add(joueur);
        }
    }

    public boolean contientJoueur(Footballeur joueur) {
        return joueurs.contains(joueur);
    }

    public ArrayList<Footballeur> getJoueurs() {
        return joueurs;
    }

    public String getNom() {
        return nom;
    }

    public Footballeur[] getEquipe() {
        return equipe;
    }

    public void faireEquipe() {
        ArrayList<Footballeur> candidats = new ArrayList<Footballeur>();
        for (Footballeur joueur : joueurs) {
            if (joueur.getPerformance() > 5) {
                candidats.add(joueur);
            }
        }
        int nbCandidats = candidats.size();
        int tailleEquipe = Math.min(nbCandidats, 11);
        equipe = new Footballeur[tailleEquipe];
        for (int i = 0; i < tailleEquipe; i++) {
            equipe[i] = candidats.get(i);
        }
    }

    public void afficher() {
        System.out.println("Club " + nom + " :");
        for (Footballeur joueur : joueurs) {
            joueur.afficher();
        }
    }
}