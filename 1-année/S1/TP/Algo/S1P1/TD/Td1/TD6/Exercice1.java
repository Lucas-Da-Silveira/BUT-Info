import java.util.*;

public class Exercice1{
        static final Scanner input =new Scanner (System.in);
    
        public static double harmonic(int n){

        double sum = 0.0;

        for (int i = 1; i<=n; i++)
            sum +=1.0/i;
        return sum;
     }

        public static void main(String[] args){

         int n;
        double somme;

        System.out.println("Entrez un nombre");
        n=input.nextInt();

        somme=harmonic(n);
        System.out.println("La somme de "+n+ " est "+somme);
            

    }
}

