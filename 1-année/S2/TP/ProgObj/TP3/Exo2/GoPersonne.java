import java.util.Scanner;
import java.time.LocalDate;

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
        System.out.println("Entrez votre sexe : ");
        char sexe = input.next().charAt(0);
        p1.setSexe(sexe);

        System.out.println("Vous vous appelez " + p1.prenom + " " + p1.nom + " et vous êtes né le " + p1.jourNaiss + "/" + p1.moisNaiss + "/" + p1.anneeNaiss + ".");
        System.out.println("Vous avez " + p1.ageEn2014() + " ans en 2014.");

        Personne p2 = new Personne();

        System.out.println("Entrez votre nom : ");
        String nom = input.nextLine();
        p1.setNom(nom);
        System.out.println("Entrez votre prénom : ");
        String prenom = input.nextLine();
        p1.setPrenom(prenom);
        p2.setDateNaiss(jour,mois,annee);
        System.out.println("Entrez votre sexe : ");
        char sexe = input.next().charAt(0);
        p1.setSexe(sexe);

        System.out.println("Vous vous appelez " + p2.prenom + " " + p2.nom + " et vous êtes né le " + p2.jourNaiss + "/" + p2.moisNaiss + "/" + p2.anneeNaiss + ".");
        System.out.println("Vous avez " + p2.ageEn2014() + " ans en 2014.");

        LocalDate birthDate1 = LocalDate.of(p1.anneeNaiss, p1.moisNaiss, p1.jourNaiss);
        LocalDate birthDate2 = LocalDate.of(p2.anneeNaiss, p2.moisNaiss, p2.jourNaiss);

        if (birthDate1.plusYears(18).isAfter(LocalDate.now()) || birthDate2.plusYears(18).isAfter(LocalDate.now())){
            System.out.println("Vous êtes mineur.");
        } else {
            System.out.println("Vous êtes majeur.");
        }

        System.out.println(p1);
        System.out.println(p2);
        
    }
}