import java.awt.*;
import java.util.Scanner;

/**
 * Created by zulupero on 14/06/16.
 */
public class Main {
    public static final Scanner input = new Scanner(System.in);
    public static void main(String[] args) throws InterruptedException {
         final int[][] casesColorees = {
        {14,0},{14,1},{14,7},{14,13},{14,14},
        {13,0},{13,6},{13,7},{13,8},{13,14},
        {12,3},{12,7},{12,11},
        {11,2},{11,3},{11,11},{11,12},
        {10,2},{10,7},{10,12},
        {9,6},{9,7},{9,8},
        {8,1},{8,5},{8,6},{8,8},{8,9},{8,13},
        {7,0},{7,1},{7,2},{7,4},{7,5},{7,9},{7,10},{7,12},{7,13},{7,14},
        {6,1},{6,5},{6,6},{6,8},{6,9},{6,13},
        {5,6},{5,7},{5,8},
        {4,2},{4,7},{4,12},
        {3,2},{3,3},{3,11},{3,12},
        {2,3},{2,7},{2,11},
        {1,0},{1,6},{1,7},{1,8},{1,14},
        {0,0},{0,1},{0,7},{0,13},{0,14}
        };

        BuggleWorld buggleWorld = BuggleWorld.getInstance();

        int x ;
        int y ;
        final int N_BUGS=1;  // nombre de Bugs
        final int Y_BUGS=2;  // position initiale Y des bugs
        final int HAUTEUR = 15; // en nb de cases
        final int LARGEUR = 15; // en nb de cases
        final int TAILLE_CASE = 40; // en pixels
        final Color COULEUR_FOND = StdDraw.GREEN;
        final Color COULEUR_PP = StdDraw.BLUE ;

        buggleWorld.init(HAUTEUR,LARGEUR,TAILLE_CASE); // init(hauteur, largeur, tileSize)

        boolean readMaze = true ; // positionner à false pour zapper la lecture des murs
        String walls ;
        while (readMaze && input.hasNext()) {
            x = Integer.valueOf(input.next());
            y = Integer.valueOf(input.next());
            walls = input.next();
            if (walls.equals("endWalls"))
                break;
            buggleWorld.setMaze(x, y, walls);
        }

        buggleWorld.setCouleurFond(COULEUR_FOND);
        buggleWorld.setBrosses(COULEUR_PP, casesColorees);

        buggleWorld.redraw();
        for(int nbb=0; nbb<N_BUGS; nbb++) {
            Bug b1 = new Bug(nbb, Y_BUGS, 0);
            buggleWorld.addBuggle(b1);
        }
        buggleWorld.redraw();
        for (Buggle b: buggleWorld.getBuggles())
            ((Bug) b).enRoute();
    }
}
