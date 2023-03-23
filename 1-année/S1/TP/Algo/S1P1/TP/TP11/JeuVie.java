import java.util.Scanner;
import java.util.Random;

public class JeuVie {
  static final Scanner input = new Scanner(System.in);
  static final Random rand = new Random();

  enum Cell{DEAD, ALIVE}
  int size ;
  Cell[][] ecosysteme ;
  int[][] voisines ;

  /**
   * Renvoie vrai avec une probabilité p
   * @param p la probabilité de tirage vrai souhaitée
   * @return  le tirage vrai ou faux
   */
    public boolean tireProba(double p){
        return (rand.nextDouble() < p);
    }

    /**
    * Constructeur d'un jeu de la vie de taille n.
    * Initialisé avec nxn cellules dont p% sont vivantes
    * @param n la taille du coté de l'écosystème
    */
    public JeuVie(int n, double p){
        size = n;
        ecosysteme = new Cell[size][size];
        voisines = new int[size][size];
        for(int i=0; i<size; i++){
            for(int j = 0; j<size; j++){
                if(tireProba(p)){
                    ecosysteme[i][j] = Cell.ALIVE;
                }else{
                    ecosysteme[i][j] = Cell.DEAD;
                }
            }
        }
    }
    public int nbVoisines(int i, int j){
        int nbVoisines = 0;
        for(int ligne = i-1; ligne<=i+1; ligne++){
            for(int col = j-1; col<=j+1; col++){
                if(ligne != i || col != j){
                    if(ecosysteme[(ligne+size)%size][(col+size)%size] == Cell.ALIVE){
                        nbVoisines++;
                    }
                }
            }
        }
        return nbVoisines;
    }

    public int dispEco(){
        int nbAlive = 0;
        for(int i=0; i<size; i++){
            for(int j = 0; j<size; j++){
                if(ecosysteme[i][j] == Cell.ALIVE){
                    nbAlive++;
                    StdDraw.setPenColor(StdDraw.RED);
                    StdDraw.filledCircle(i+0.5, j+0.5, 0.5);
                }else{
                    StdDraw.setPenColor(StdDraw.WHITE);
                    StdDraw.square(i+0.5, j+0.5, 0.5);
                }
                if(size<30){
                    StdDraw.setPenColor(StdDraw.BLACK);
                    StdDraw.text(i+0.5, j+0.5, Integer.toString(nbVoisines(i,j)));
                }
            }
        }
        return nbAlive;
    }

    public int nextGen(){
        int changEtat = 0;
        for(int i=0; i<size; i++){
            for(int j = 0; j<size; j++){
                voisines[i][j] = nbVoisines(i, j);
            }
        }
        for(int i=0; i<size; i++){
            for(int j = 0; j<size; j++){
                if(ecosysteme[i][j] == Cell.ALIVE){
                    if(voisines[i][j] < 2|| voisines[i][j] > 3){
                        ecosysteme[i][j] = Cell.DEAD;
                        changEtat++;
                    }
                }else{
                    if(voisines[i][j] == 3){
                        ecosysteme[i][j] = Cell.ALIVE;
                        changEtat++;
                    }
                }
            }
        }
        return changEtat;
    }

    public static void main(String[] args) {
        int n ; // taille de l'ecosystème
        int nb; // nombre de générations à sauter entre deux affichages
        int gen =0; // numéro de la génération courante
        int cpt; // compteur de générations à sauter entre deux affichages
        int ch;  // nombre de changements d'une génération à l'autre
        final double PROBA = 0.5 ; // pour l'initialisation

        System.out.println("Taille de l'ecosystème : ");
        n = input.nextInt();

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(0, n); // mise à l'échelle de la fenêtre

        JeuVie jeu = new JeuVie(n,PROBA) ; // création du jeu
        jeu.dispEco();                      // affichage initial

        do{
        System.out.println("Nombre de générations à sauter entre deux affichages : ");
        nb = input.nextInt();
        cpt = nb;
        do{
            ch = jeu.nextGen(); // calcul de la génération suivante
            cpt--;
        if(cpt == 0){
            cpt = nb;
            StdDraw.clear();
            jeu.dispEco();
            StdDraw.show();
        }
        gen+=nb;
        } while (ch != 0);
        System.out.println("Plus de changements à la génération " + gen);
        System.out.println("Voulez-vous continuer ? (1:oui, 0:non)");
        ch = input.nextInt();
        if(ch == 0){
            System.out.println("Au revoir");
        }
        } while (ch != 0);
    }
}
