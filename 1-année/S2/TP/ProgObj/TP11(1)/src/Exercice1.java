import java.util.*;;


public class Exercice1 {
    static int[] tableau = {17, 12, 15, 38, 29, 157, 89, -22, 0, 5}; 

    static int division(int indice, int diviseur){ 
        return tableau[indice]/diviseur;  
    }  
    public static void main(String[] args){  
        // int x, y;  
        // ecrire("Entrez l’indice de l’entier à diviser: ");  
        // x = lireInt();  
        // ecrire("Entrez le diviseur: ");  
        // y = lireInt();  
        // ecrire("Le résultat de la division est: ");  
        // ecrire(division(x,y));  

        int x, y;
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.print("Entrez l'indice de l'entier à diviser : ");
            x = scanner.nextInt();
            System.out.print("Entrez le diviseur : ");
            y = scanner.nextInt();
            System.out.print("Le résultat de la division est : ");
            System.out.println(division(x, y));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("L'indice fourni est invalide. Veuillez entrer un indice valide.");
        } catch (ArithmeticException e) {
            System.out.println("Une erreur de division par zéro s'est produite.");
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite : " + e.getMessage());
        }
    }  
}
