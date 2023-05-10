public class Exercice1 {
    static int[] tableau = {17, 12, 15, 38, 29, 157, 89, -22, 0, 5}; 
    static int division(int indice, int diviseur){ 
    return tableau[indice]/diviseur;  
    }  
    public static void main(String[] args){  
        int x, y;  
        ecrire("Entrez l’indice de l’entier à diviser: ");  
        x = lireInt();  
        ecrire("Entrez le diviseur: ");  
        y = lireInt();  
        ecrire("Le résultat de la division est: ");  
        ecrire(division(x,y));  
    }  
}
