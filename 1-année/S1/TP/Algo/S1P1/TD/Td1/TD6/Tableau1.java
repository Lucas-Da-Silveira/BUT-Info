import java.util.*;

class Tableau1{

        static final Scanner input = new Scanner (System.in);
        static final int bornMax = 10;

        static int[] generateTab(int tab[], int n){
                for(int it=0; it<n; it++){
                    tab[it]=(int)(Math.random()*bornMax) - (bornMax/2);
                }
            return tab;
        }

        static int [] additionTab ( int tab1[], int tab2[], int n){
                int sommeTab[]=new int[n];
                for (int it=0; it<n; it++){
                    sommeTab[it]=(tab1[it]+tab2[it]);

                }
            return sommeTab;
        }

        static int[] tabPositiveNegative(int tab[], int n){
            int tabPosNeg[]=new int[2];
            int nbPositive=0;
            int nbNegative=0;
            for(int it=0; it<tab.length; it++){
                if(tab[it]>=0)
                        nbPositive++;
                    else
                        nbPositive++;
            }
        tabPosNeg[0]=nbPositive;
        tabPosNeg[1]=nbNegative;
        return tabPosNeg;
        }

     

        static void printTab( int tab[], int n){
                for (int it=0; it<n; it++)
                    System.out.print(tab[it]+"\t");
                System.out.println("");
        }

        public static void main(String[] args){

            int n;

            do{
            System.out.println("Entrer n:");
            n=input.nextInt();
            }while(n<1);

            int tab1[] = new int[n];
            int tab2[] = new int[n];
            int sommeTab[] = new int[n];

            generateTab(tab1, n);
            System.out.print("Table 1=\t");
            printTab(tab1,n);

            System.out.print("\n Table 2=\t");
            generateTab(tab2, n);
            printTab(tab2, n);
            System.out.print("\n Table 1+ Table 2 :");
            sommeTab= additionTab(tab1,tab2, n);
            printTab(sommeTab,n);
        }
}
