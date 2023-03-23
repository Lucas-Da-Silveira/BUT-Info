import java.util.*;

public class Ordinateur {

    private String marqueP;
    private double frequence;
    private String marqueE;
    private int taille;

    
    public Ordinateur(){
        marqueP = "";
        frequence = 0;
        marqueE = "";
        taille = 0;
    }

    public Ordinateur(String e, String p ){
        this.marqueE = e;
        this.marqueP = p;
    }

    public void init(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez la marque du processeur : ");
        marqueP = sc.nextLine();
        System.out.println("Entrez la fréquence du processeur : ");
        frequence = sc.nextDouble();
        System.out.println("Entrez la marque de l'écran : ");
        marqueE = sc.nextLine();
        System.out.println("Entrez la taille de l'écran : ");
        taille = sc.nextInt();
    }
}
