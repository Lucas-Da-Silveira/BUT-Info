import java.util.ArrayList;

public class Joueur extends Cartes{

    protected String nom;
    ArrayList<String> joueur1 = new ArrayList<String>(paquet.subList(0, 16));
    ArrayList<String> joueur2 = new ArrayList<String>(paquet.subList(16, 32));

    public Joueur(String nom){
        this.nom = nom;
    }

    public String getNom(){
        return nom;
    }

    public String toString(){
        String str = "";
        for (int i = 0; i < paquet.size(); i++){
            str += paquet.get(i) + "\n";
        }
        return str;
    }

    public void ajouterCarte(String carte){
        paquet.add(carte);
    }

    public String tirerCarte(){
        String carte = paquet.get(0);
        paquet.remove(0);
        return carte;
    }

    public int getNbCartes(){
        return paquet.size();
    }

    public void melanger(){
        for (int i = 0; i < nbCartes; i++){
            int random = (int) (Math.random() * nbCartes);
            String temp = paquet.get(i);
            paquet.set(i, paquet.get(random));
            paquet.set(random, temp);
        }
    }

    public void jouerCarte(){
        String carte = paquet.get(0);
        paquet.remove(0);
        System.out.println(nom + " joue " + carte);
    }

    public void gagnerCarte(String carte){
        paquet.add(carte);
    }

    public boolean aPerdu(){
        return paquet.isEmpty();
    }

    public void afficherCartes(){
        System.out.println(nom + " a " + paquet.size() + " cartes");
    }

    public void afficherCarte(){
        System.out.println(nom + " a " + paquet.get(0));
    }
}