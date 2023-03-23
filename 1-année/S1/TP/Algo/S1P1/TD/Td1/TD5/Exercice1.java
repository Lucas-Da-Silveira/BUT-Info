import java.util.*;

class Exercice1{

    static final Scanner sc = new Scanner (System.in) ;

    public static void main(String[] args)
    {

        int i;

        do{
            System.out.println("Entrez un nombres compris entre 1 et 3 :");
            i = sc.nextInt();   
        }while (i<1 || i>3);

        System.out.println("Nombre correct" +i);
    }
}