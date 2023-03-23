import java.util.*;

class Exercice9{
    static final Scanner input =new Scanner (System.in);

    public static void main(String []args){
        int n;
        String resultat ;

        do{
            System.out.println("Entrez un nombre :");
            n = input.nextInt();
        }while(n<= 0);

        for(int k=1; k<n ; k++){
            resultat ="";
        
            for(int j=1; j<n; j++){
            resultat += j;
            System.out.println(resultat);
          }
         
        }

    }

}