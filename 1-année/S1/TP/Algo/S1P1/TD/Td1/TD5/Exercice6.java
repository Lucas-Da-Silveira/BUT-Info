import java.util.*;

// class Exercice6{
//     static final Scanner input =new Scanner (System.in);

//     public static void main(String []args){
//         int nb;
//         int fact = 1;

//         System.out.println("Entrez un nombre :");
//         nb =input.nextInt();

//         for (int k = 2 ; k <= nb ; k++){
//             fact *=k;
            
//         } 
//         System.out.println("Le factorielle de " +nb +"est :" +fact);
//     }
// }


class Exercice6{
    static final Scanner input =new Scanner (System.in);

    public static int factorial(int nb){
        int fact =1;

        for (int k=2; k<=nb; k++)
            fact *=k;

        return fact;
    }
    public static void main(String [] args){
        int nb;
        int facto;
        
        System.out.println("Entrez un nombre :");
        nb = input.nextInt();

        facto=factorial(nb);
        System.out.println("Le factorielle de " +nb +"est :" +facto);
    }

}