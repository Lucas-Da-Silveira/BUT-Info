import java.util.*;

// class Exercice7{
//     static final Scanner input =new Scanner (System.in);

//     public static void main(String []args){
//        int n;
//        int nombre;
//        int nombremax;

//        System.out.println("Entrez un nombre :");
//        n=input.nextInt();
//        System.out.println("Entrez le premier nombre :");
//        nombre=input.nextInt();
//        nombremax=nombre;

//        for (int k = 2 ; k <=n ; k++)
//         System.out.println("Entrez le "+k+"nombre" );
//         nombre = input.nextInt();

//         if(nombre > nombremax)
//             nombre = nombremax;
//         
//         System.out.println("Le nombre le plus grand est :"+nombremax);
//     }   
    
// }

class Exercice7{
    static final Scanner input =new Scanner (System.in);

    public static int nombremax(int n){
        int nombre;
        int nombremax =0;

        for (int k = 2 ; k <=n ; k++)
         System.out.println("Entrez le "+k+"nombre" );
         nombre = input.nextInt();

        if(nombre > nombremax)
             nombre = nombremax;
        return nombremax;
        


    }


    public static void main(String []args){

    }

}
