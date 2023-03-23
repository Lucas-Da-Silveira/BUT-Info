import java.util.Scanner;

class GoPersonne{

    public static void main(String[] args){

        Scanner input = new Scanner(System.in);

        Personne p1 = new Personne();

        System.out.println("Entrez votre nom : ");
        String nom = input.nextLine();
        p1.setNom(nom);

        System.out.println("Entrez votre prénom : ");
        String prenom = input.nextLine();
        p1.setPrenom(prenom);

        System.out.println("Entrez votre jour de naissance : ");
        int jour = input.nextInt();
        System.out.println("Entrez votre mois de naissance : ");
        int mois = input.nextInt();
        System.out.println("Entrez votre année de naissance : ");
        int annee = input.nextInt();
        p1.setDateNaiss(jour,mois,annee);

        System.out.println("Vous vous appelez " + p1.prenom + " " + p1.nom + " et vous êtes né le " + p1.jourNaiss + "/" + p1.moisNaiss + "/" + p1.anneeNaiss + ".");
        System.out.println("Vous avez " + p1.ageEn2014() + " ans en 2014.");

        Personne p2 = new Personne();

        System.out.println("Entrez votre nom : ");
        String nom = input.nextLine();
        p1.setNom(nom);

        System.out.println("Entrez votre prénom : ");
        String prenom = input.nextLine();
        p1.setPrenom(prenom);

        p2.setDateNaiss(p1.jourNaiss, p1.moisNaiss, p1.anneeNaiss);

        System.out.println("Vous vous appelez " + p2.prenom + " " + p2.nom + " et vous êtes né le " + p2.jourNaiss + "/" + p2.moisNaiss + "/" + p2.anneeNaiss + ".");
        System.out.println("Vous avez " + p2.ageEn2014() + " ans en 2014.");

        System.out.println(p1);
        System.out.println(p2);
    }
}