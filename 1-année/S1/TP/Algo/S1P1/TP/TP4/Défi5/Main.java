import java.awt.*;
import java.util.Scanner;

/**
 * Created by zulupero on 14/06/16.
 */
public class Main {
    public static final Scanner input = new Scanner(System.in);
    public static void main(String[] args) throws InterruptedException {
        final int[][] casesColorees = {
        {14,0},{14,1},{14,2},{14,3},{14,4},{14,5},{14,6},{14,7},
        {13,0},{13,1},{13,6},{13,7},
        {12,1},{12,6},
        {11,1},{11,2},{11,5},{11,6},
        {10,0},{10,1},{10,2},{10,3},{10,4},{10,5},{10,6},{10,7},
        {9,1},{9,6},
        {8,0},{8,1},{8,2},{8,3},{8,4},{8,5},{8,6},{8,7},
        {5,2},{5,6},
        {4,1},{4,6},
        {3,1},{3,3},{3,5},{3,7},
        {2,4},
        {1,6}
        };
        BuggleWorld buggleWorld = BuggleWorld.getInstance();

        int x ;
        int y ;
        final int N_BUGS=8;  // nombre de Bugs
        final int Y_BUGS=0;  // position initiale Y des bugs
        final int HAUTEUR = 15; // en nb de cases
        final int LARGEUR = 8; // en nb de cases
        final int TAILLE_CASE = 40; // en pixels
        final Color COULEUR_FOND = StdDraw.WHITE;
        final Color COULEUR_PP = StdDraw.GREEN ;

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
