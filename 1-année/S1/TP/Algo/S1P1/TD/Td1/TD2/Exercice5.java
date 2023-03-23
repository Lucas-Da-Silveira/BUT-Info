import java.util.*;

class Exercice5 {

    static final Scanner input =new Scanner (System.in);
    public static void main(String[]args){
        int duree;
        int heure;
        int minutes;
        int secondes;

        System.out.println("Entrez une durée");
        duree=input.nextInt();

        secondes = duree*60*60;
        System.out.println("Secondes :" +secondes);

        minutes = secondes/60;
        System.out.println("Minutes :" +minutes);

        heure = minutes/60; 
        System.out.println("Heure :" + heure);

    }
}