import java.util.*;

public class Ecran {
    
    private String marqueE;
    private int taille;

    public Ecran(){
        marqueE = "";
        taille = 0;
    }

    public Ecran(String m, int t){
        this.marqueE = m;
        this.taille = t;
    }

    public String getMarqueE(){
        return marqueE;
    }

    public void init(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez la marque de l'écran : ");
        marqueE = sc.nextLine();
        System.out.println("Entrez la taille de l'écran : ");
        taille = sc.nextInt();
    }

    public int getTaille(){
        return taille;
    }

    public String toString(){
        return "Marque de l'écran : " + marqueE + " Taille : " + taille;
    }
}
