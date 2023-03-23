class Revision{

    /**
    * Affiche les éléments du tableau en paramère
    * @param tab
    */
    public static void affiche(int[] tab){
        for(int i = 1; i<= tab[0]; i++){
            System.out.print(tab[i] + " ");
        }
    }

    public static int taille(int[] tab){
        return tab[0];
    }

    public static boolean estVide(int[] tab){
        return (taille(tab) == 0);
    }

    public static boolean estPlein(int[] tab){
        return (taille(tab) + 1>= tab.length);
    }

    public static void initialise(int[] tab, int k){
        for(int i = 0; i <= k; i++){
            tab[i] = i;
        }
        tab[0] = k;
    }

    public static int indice(int[] tab, int x){
        /*V1
        int i = 1;
        while(i <= tab[0] && tab[i] <= x){
            if(tab[i] == x){
                return i;
            }
                i++;
        }
        return -1;  
        */
        int min = 1;
        int max = taille(tab);
        int milieu;
        int trouve = 1;
        while(trouve == -1 && min <= max){
            milieu = (min + max) / 2;
            if(tab[milieu] == x){
                trouve = 1;
                return milieu;
            }else if(tab[milieu] < x){
                min = milieu + 1;
            }else{
                max = milieu - 1;
            }
        }
        return -1;
    }

    public static int supprimeFin(int[] tab){
        if (!estVide(tab)){
            tab[0]--;
        }
        return tab[taille(tab)];
    }

    public static void ajputeFin(int[] tab, int x){
        if (!estPlein(tab) && x >= tab[taille(tab)]){
            tab[0]++;
            tab[taille(tab)] = x;
        }
    }

    public static void decalageGauche(int[] tab, int i, int j){
        for(int k = i; k <= j; k++){
            tab[k] = tab[k-1];
        }
    }

    public static void decalageDroite(int[] tab, int i, int j){
        for(int k = j; k >= i; k--){
            tab[k+1] = tab[k];
        }
    }

    public static void insere(int[] tab, int x){
        if(!estPlein(tab) && indice(tab,x)==-1){
            int i = 1;
            while(i <= tab[0] && tab[i] <= x){
                i++;
            }
            decalageDroite(tab, i, tab[0]);
            tab[i] = x;
            tab[0]++;
        }
    }

    public static void supprime(int[] tab, int x){
        if(!estVide(tab) && indice(tab,x)!=-1){
            int i = indice(tab,x);
            decalageGauche(tab, i, tab[0]);
            tab[0]--;
        }
    }

    public static void main (String[] args){
        int size = 10;
        int tab[] = new int[size];

        //tableau départ
        initialise(tab, 5);
        affiche(tab);

        //insertion d'un nombre
        System.out.println();
        insere(tab, 7);
        affiche(tab);

        //supression de n éléments
        System.out.println();
        supprime(tab,2);
        affiche(tab);

        //supprime le dernier élément
        System.out.println();
        supprimeFin(tab);
        affiche(tab);

        //ajoute un élément à la fin
        System.out.println();
        ajputeFin(tab, 6);
        affiche(tab);

        //Creé un décalage à gauche
        System.out.println();
        decalageGauche(tab, 2, 4);
        affiche(tab);

        //Creé un décalage à droite
        System.out.println();
        decalageDroite(tab, 2, 4);
        affiche(tab);
        
        
    }
}