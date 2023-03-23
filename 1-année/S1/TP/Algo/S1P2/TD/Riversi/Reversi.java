import java.util.Arrays;
import java.util.Scanner;

public class Reversi {
    public static final Scanner input = new Scanner(System.in);

    static final int NMAX = 9;

    public static void main(String[] args) {
        int[] tab = new int[NMAX];
        int[] originalTab = new int[NMAX];
        int nbCoups;

        initTab(originalTab);

        do {
            nbCoups = 0;

            System.out.println("Voici le tableau : ");
            afficheTab(originalTab);

            copieTab(originalTab, tab);

            do {
                // int nb = lireNb();
                int nb = joue(tab);
    
                permuteTab(tab, nb);
                afficheTab(tab);
    
                nbCoups++;
            } while (!verifTab(tab));
    
            System.out.println("Vous avez gagné la partie en " + nbCoups + " coup" + (nbCoups > 1 ? "s" : "") + ". Bien joué !");

                System.out.print("Souhaitez vous rejouer avec le même mélange initial (o/n) ? ");
        } while (input.next().charAt(0) == 'o');

        System.out.println("Merci d'avoir joué !");
    }

    /**
     * Développe une stratégie pour gagner au jeu
     * @param tab
     * @return le meilleur nombre à jouer
     */
    public static int joue(int[] tab) {
        int j = tab.length - 1;

        while (tab[j] == j + 1) {
            j--;
        }

        int i = 0;
        
        while (i < tab.length && tab[i] != j + 1) {
            i++;
        }

        if (i == 0) {
            return (j + 1);
        }
        return (i + 1); 
    }

    /**
     * Recopie les éléments de src dans dst.
     * @param src tableau source d'entiers
     * @param dest tableau destination d'entiers
     */
    public static void copieTab(int[] src, int[] dest) {
        for (int i = 0; i < src.length; i++) {
            dest[i] = src[i];
        }
    }

    /**
     * Met les bonnes valeurs dans le tableau en paramètre puis les mélange.
     * @param tab
     */
    public static void initTab(int[] tab) {
        for (int i = 0; i < tab.length; i++) {
            tab[i] = i + 1;
        }

        melangeTab(tab);
    }

    /**
     * Mélange le tableau donné en paramètre.
     * @param tab
     */
    public static void melangeTab(int[] tab) {
        int tmp, randVal;

        for (int i = 0; i < tab.length - 1; i++) {
            tmp = tab[i];
            randVal = nombreAleatoire(i + 1, tab.length);

            tab[i] = tab[randVal];
            tab[randVal] = tmp;
        }
    }

    /**
     * Retourne un nombre aléatoire entre [min, max[
     * @param min
     * @param max
     * @return
     */
    public static int nombreAleatoire(int min, int max) {
        return min + (int)(Math.random() * (max - min));
    }

    /**
     * Affiche les éléments du tableau passé en paramètre.
     * @param tab
     * 
     */
    public static void afficheTab(int[] tab) {
        System.out.print("[");
        System.out.print(tab[0]);
        for (int i = 1; i < tab.length; i++) {
            System.out.print(", " + tab[i]);
        }
        System.out.print("]");
        System.out.println("");
    }

    /**
     * Lit un entier au clavier compris entre 1 et NMAX
     * @return 
     */
    public static int lireNb() {
        int nb;

        do {
            System.out.print("Entrez un nombre [2-" + NMAX + "] : ");
            nb = input.nextInt();
        } while(nb <= 1 || nb > NMAX);

        return nb;
    }

    /**
     * Retourne les nb premiers éléments du tableau en paramètre.
     * @param tab
     * @param nb
     */
    public static void permuteTab(int[] tab, int nb) {
        int tmp;

        for (int i = 0; i < nb/2; i++) {
            tmp = tab[i];
            tab[i] = tab[nb-1-i];
            tab[nb-1-i] = tmp;
        }
    }

    /**
     * Renvoie vrai si le tableau en paramètre est bien [1, 2, 3, ..., NMAX], faux sinon.
     * @param tab
     * @return
     */
    public static boolean verifTab(int[] tab) {
        int i = 0;

        while (i < tab.length && i + 1 == tab[i]) {
            i++;
        }

        return i == tab.length;
    }
}
