import java.util.Scanner;

class Age{
        
    public static void main(String[]args){
        final int ANNEE_COURANTE = 2022;
        int annee;
        int age;
        String prenom;

        try (Scanner myObj = new Scanner(System.in)) {
            System.out.println("entrez votre prénom :");
            prenom = myObj.nextLine();
            
            System.out.println("entrez votre année de naissance :");
            annee = myObj.nextInt();
        }
        
        age = ANNEE_COURANTE - annee;
        System.out.println("Vous avez donc "+age +" ans ");
        System.out.println("Et votre prénom est :"+ prenom);
    }
}
