import java.util.*;

class Exercice2{
    static final Scanner myObj = new Scanner(System.in);
    
    public static void main(String[]args){
        int numéro1=0;
        int numéro2=0;
        float diamètre1=0;
        float diamètre2=0;
        float prix1=0;
        float prix2=0;
        float rapport1=0;
        float rapport2=0;

        System.out.println("Entrez numéro :");
        numéro1= myObj.nextInt();
        System.out.println("Entrez Diamètre1 :");
        diamètre1= myObj.nextFloat();
        System.out.println("Entrez prix1 :");
        prix1= myObj.nextFloat();

        System.out.println("Entrez numéro :");
        numéro2= myObj.nextInt();
        System.out.println("Entrez Diamètre2 :");
        diamètre2= myObj.nextFloat();
        System.out.println("Entrez prix2 :");
        prix2= myObj.nextFloat();

        rapport1 = diamètre1/prix1;
        System.out.println("Le numéro "+numéro1+ " a un rapport de :"+rapport1);

        rapport2 = diamètre2/prix2;
        System.out.println("Le numéro "+numéro2+ " a un rapport de : "+rapport2);

    }
} 