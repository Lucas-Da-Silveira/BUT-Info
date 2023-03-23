import java.util.*;

public class Processeur {

    private String marqueP;
    private double frequence;
   
    public Processeur(){
        marqueP = "";
        frequence = 0;
    }

    public Processeur(String m, double f){
        this.marqueP = m;
        this.frequence = f;
    }

    public double getFrequence(){
        return frequence;
    }

    public String getMarqueP(){
        return marqueP;
    }

    public String toString(){
        return "Marque du processeur : " + marqueP + " Frequence : " + frequence;
    }

    public void init(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez la marque du processeur : ");
        marqueP = sc.nextLine();
        System.out.println("Entrez la fréquence du processeur : ");
        frequence = sc.nextDouble();
    }
}
