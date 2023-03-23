import java.util.*;


class Exercice1{
    static Scanner myObj = new Scanner(System.in);

    public static void main(String[]args){
        double longueur=0;
        double largeur=0;
        double hauteur=0;
        double vu=0;
        double volume=0;
        int nombre=0;

        System.out.println("Entrez la longueur de la pièce :");
        longueur = myObj.nextDouble();
        System.out.println("Entrez la largeur de la pièce :");
        largeur = myObj.nextDouble();
        System.out.println("Entrez la hauteur de la pièce :");
        hauteur = myObj.nextDouble();
        System.out.println("Entrez la capacité vu :");
        vu = myObj.nextDouble();

        volume= longueur*largeur*hauteur;
        nombre= (int)Math.ceil(volume/vu);
        System.out.println("Le nombre de radiateur nécesssaire est :"+nombre);
        
    }
}