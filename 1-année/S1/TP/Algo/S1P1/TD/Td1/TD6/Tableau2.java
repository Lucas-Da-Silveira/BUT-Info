import java.util.*;
class Tableau2{

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

        static int schtroumf (int tab1[], int tab2[], int n){
            int sum=0;
            for (int it1=0; it1<tab1.length; it1++){
                for (int it2=0; it2<tab2.length; it2++)
                sum+=(tab1[it1]*tab2[it2]);
                }
            return sum;
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
