import java.util.*;

class Hello3{
    static final Scanner input = new Scanner(System.in);
    public static void main(String[]args){
        int entier;
        double reel;
        String chaine;
        System.out.println("Entrez un nombre entier : ");
        entier=input.nextInt();
        System.out.println("Donner un mot : ");
        chaine=input.next();
        System.out.println("Valeurs saisie: " + entier+ " " + chaine);
    }
}