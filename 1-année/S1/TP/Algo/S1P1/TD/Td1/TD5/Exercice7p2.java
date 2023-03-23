import java.util.*;

class Exercice7{
    static final Scanner input =new Scanner (System.in);

    public static void main(String []args){
       int n;
       int nombre;
       int nombremax;
       int nombremin;

       do{
        System.out.println("Entrez un nombre :");
       n=input.nextInt();
       System.out.println("Entrez le premier nombre :");
       nombre=input.nextInt();
       nombremax=nombre;
       nombremin=nombre;
       }while( n<=0 );

       for (int k = 2 ; k <=n ; k++){
        System.out.println("Entrez le "+k+"nombre" );
        nombre = input.nextInt();

        if(nombre > nombremax){
            nombre = nombremax;
        }

        if(nombre> nombremin){
            nombre = nombremin;
            }
        }
        System.out.println("Le nombre le plus grand est :"+nombremax);
        System.out.println("Le nombre le plus petit est :"+nombremin);
    }   
    
}