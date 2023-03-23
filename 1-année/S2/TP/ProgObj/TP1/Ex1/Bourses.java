import java.util.*;

class Bourses{
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);


        int age;
        int revenu;
        int nbEnfants;
        int revenuTot;
        int serviceMilitaire;

        System.out.println("Entrez votre age : ");
        age = sc.nextInt();

        System.out.println("Entrez votre revenu : ");
        revenu = sc.nextInt();

        System.out.println("Entrez le nombre d'enfants : ");
        nbEnfants = sc.nextInt();

        System.out.println("Avez vous fait le service militaire ? (1 = oui, 0 = non) : ");
        serviceMilitaire = sc.nextInt();

        revenuTot = revenu + ((40 * nbEnfants) * 12);

        if(serviceMilitaire == 0){
            if(nbEnfants >= 3 || age < 26 && revenuTot < 1200 ){
                System.out.println("Bourse accordée");
            }
            else{
                System.out.println("Bourse refusé");
            }
        }
        else if(serviceMilitaire == 1){
            if(nbEnfants >= 3 || age < 27 && revenuTot < 1200){
                System.out.println("Bourse accordée");
            }
            else{
                System.out.println("Bourse refusé");
            }
        }

    }
}