import java.util.*;

class Exercice15{
    static final Scanner input =new Scanner (System.in);

    public static void main(String []args){
        int n;
        double resultat= 0;
    do{
        System.out.println("Entrez un nombre");
        n=input.nextInt();
    }while(n<=0);
    
    for (int k = 0; k<n ; k++){
        resultat+= 1.0/(k+1);
    }
    System.out.println(resultat);
    

    }

}