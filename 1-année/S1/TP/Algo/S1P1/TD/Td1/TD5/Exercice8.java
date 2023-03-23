import java.util.*;

class Exercice8{
    static final Scanner input =new Scanner (System.in);

    public static void main(String []args){
        int n;
        int m;
        char caractere;

        System.out.println("Entrez un caractère :");
        caractere = input.next().charAt(0);

        do{
            System.out.println("Entrez un nombre :");
            n= input.nextInt();
        }while(n<= 0);

        do{
            System.out.println("Entrez un nombre :");
            m= input.nextInt();
        }while(m<=0);

        for (int k = 0; k<m; k++){
            System.out.print(caractere);
        }

        for( int k =1 ; k<n ; k++){
           System.out.print(caractere);
            
        }
        

    }

}