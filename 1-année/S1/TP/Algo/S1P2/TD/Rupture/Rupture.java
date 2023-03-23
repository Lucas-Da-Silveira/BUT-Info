import java.util.*;

class Rupture{

    /**
    * Renvoie un tableau contenant les indices de début des séquences de rupture
    * @param tab le tableau des entier
    * @return le tableau des indices de début de séquence (dans l'ordre initial)
     */

    public static int[] rupture(int[] tab){
        int cptRupts = 1;
        for (int i = 1; i<tab.length; i++)
            if (tab[i - 1] >= tab[i]){
                cptRupts++;
            }
    

        int[] tRupts = new int[cptRupts];
        tRupts[0] = 0;
        int j = 1;
        for (int i = 1; i<tab.length; i++){
            if (tab[i - 1] >= tab[i]){
                tRupts[j] = i;
                j++;
            }
        }
        return tRupts;
    }

    /**
    * Tri par insertion
    * @param tab
    * @return
    */
    public static int[][] triSequences(int[][] tab){
        int taille = tab.length;
        int[] valeur;
        int place;

        int[][] resTab = new int [tab.length][];
        for (int i = 0 ; i <tab.length; i++){
            tab[i] = sequence[i].clone;
        }

        for(int limite=1 ; limite < taille-1; limite++){
            valeur = resTab[limite].clone();
            place = limite;
            while(place > 0 && resTab[place-1].length > valeur.length){
                resTab[place] = resTab[place-1].clone();
                place--;
            }
            resTab[place] = valeur;
        }
        return resTab;
    } 


    /**
    * Renvoi un tableau 2D dont chaque élément est la i ème séquence
    * contenue dans le tableau en paramètre
    * @param tab le tableau d'entier en entrée
    * @return le tableu en 2D
    */
    public static int[][] sequences(int[] tab){
        int[] tRupture = rupture(tab);
        int nbSequence = tRupture.length;
        int[][] tSequence = new int[nbSequence][];

        for (int sequence=0; sequence<nbSequence; sequence++ ){
            int tailleSeq = tRupture[sequence + 1] - tRupture[sequence];
            tSequence[sequence] = new int[tailleSeq];
            for(int i=0; i<tailleSeq; i++){
                tSequence[sequence][i] = tab[tRupture[sequence]+i];
            }
        }
        //La dernière séquence
        int tailleLastSeq = tab.length - tRupture[nbSequence - 1];
        tSequence[nbSequence - 1] = new int[tailleLastSeq];
        for(int i=0; i<tailleLastSeq; i++){
            tSequence[nbSequence -1][i] = tab[tRupture[nbSequence - 1]+i];
        }
        return tSequence;
    }

    /**
    * Affiche le contenu du tableau 2D en paramètre
    * @param tab
     */
    public static void afficheTab2D(int[][] tab){
        System.out.print("[ ");
        for(int  i=0; i<tab.length ; i++){
            afficheTab1D(tab[i]);
            }
        System.out.println("]");
    }
    /**
    * Affiche le contenu du tableau en paramètre
    * @param tab
     */
    public static void afficheTab1D(int[] tab){
        System.out.print("[");
        for (int i=0; i<tab.length; i++){
            System.out.print(tab[i] + " ");
        }
        System.out.println("]");

    }

    public static void main (String[] args){
        int[] tIn = {1,2,5,7,2,6,0,5,2,4,6,7,8,9,3,4,6,1,2,7,8,9,4,2,3,1,5,9,7,1,6,6,3};
        /**int [][] t2DIn = {{1,2,5,7},{2,6},{0,5},{2,4,6,7,8,9},{3,4,6},{1,2,7,8,9},{4},{2,3},{1,5,9},{7},{1,6},{6},{3}};*/
        /**afficheTab2D(t2DIn);*/
        /**afficheTab2D(sequences(tIn));*/
        int[][] tSeq = sequences(tIn);
        Array.sort(tSeq, (t1, t2) -> Interger.compare(t2.length, t1.length));
        afficheTab2D(tSeq);
    }

}