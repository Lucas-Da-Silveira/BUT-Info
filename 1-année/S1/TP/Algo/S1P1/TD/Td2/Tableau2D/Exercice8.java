import java.util.*;



class Exercice8{

    static final Scanner input = new Scanner (System.in);

    public static void main(String [] args){

        double [][] a;
        int i , j;

        int M;
        int N;

        double somme = 0;

        System.out.println("Entrez une valeur entre 10 et 100");
        M = input.nextInt();
        System.out.println("Entrez une valeur entre 10 et 100");
        N = input.nextInt();

        a = new double [M][N];

        for(  i=0; i<a.length ; i++){
            for (  j=0; j<a[i].length; j++){
                a[i][j] = (int)(Math.random()*90);
                System.out.print(a[i][j] + "");
            }
            System.out.println();
            
        
        for(int k = 0; k < a.length; k++){
            somme = somme + a[i][j];
        }
        double moyenne = somme / a.length;
        
        System.out.print("\nMoyenne = "+moyenne);
        }
        System.out.println();

    }
}