import java.util.*;

class Exercice3{
    static final Scanner myObj = new Scanner(System.in);
    public static void main(String[]args){
        int mois = 0;

        System.out.println("Entrez un numéro :");
        mois= myObj.nextInt();

       switch(mois){
            case 1 : System.out.println("Janvier" +" 31"); break;
            case 2 : System.out.println("Février" +" 28"); break;
            case 3 : System.out.println("Mars"+" 31"); break;
            case 4 : System.out.println("Avril"+" 30"); break;
            case 5 : System.out.println("Mai"+" 31"); break;
            case 6 : System.out.println("Juin"+" 30"); break;
            case 7 : System.out.println("Juillet"+" 31"); break;
            case 8 : System.out.println("Août"+" 31"); break;
            case 9 : System.out.println("Septembre"+" 30"); break;
            case 10 : System.out.println("Octobre"+" 30"); break;
            case 11 : System.out.println("Novembre"+" 30"); break;
            case 12 : System.out.println("Décembre"+" 31"); break;
            default : System.out.println("Nombre éronné");
       }
    }
}