import java.util.*;

class Footballeur {
    private String nom;
    private double performance;
    private Club clubEmployeur;

    public Footballeur(String nom, double performance, Club clubEmployeur) {
        this.nom = nom;
        this.performance = performance;
        this.clubEmployeur = clubEmployeur;
    }

    public void setClubEmployeur(Club clubEmployeur) {
        this.clubEmployeur = clubEmployeur;
    }

    public void init() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nom du footballeur : ");
        nom = sc.nextLine();
        System.out.print("Performance du footballeur : ");
        performance = sc.nextDouble();
    }

    public String getNom() {
        return nom;
    }

    public double getPerformance() {
        return performance;
    }

    public void afficher() {
        System.out.println(nom + ", performance : " + performance + ", club employeur : " + clubEmployeur.getNom());
    }
}