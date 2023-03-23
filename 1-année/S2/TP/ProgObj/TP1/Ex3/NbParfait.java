import java.util.*;

class NbParfait{

    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);

        int nombre;

        System.out.println("Entrez un nombre : ");
        nombre = sc.nextInt();

        int somme = 0;

        for (int i = 1; i < nombre; i++){
            if (nombre % i == 0){
                somme += i;
            }
        }

        if (somme == nombre){
            System.out.println(nombre + " est parfait");
        }
        else{
            System.out.println(nombre + " n'est pas parfait");
        }
        
    }
}