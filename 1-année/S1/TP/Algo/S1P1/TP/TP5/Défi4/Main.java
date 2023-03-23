import java.awt.*;
import java.util.Scanner;

/**
 * Created by zulupero on 14/06/16.
 */
public class Main {
    public static final Scanner input = new Scanner(System.in);
    public static void main(String[] args) throws InterruptedException {
        final int[][] casesColorees = {

        };
        BuggleWorld buggleWorld = BuggleWorld.getInstance();

        int x ;
        int y ;
        final int N_BUGS=1;  // nombre de Bugs
        final int Y_BUGS=6;  // position initiale Y des bugs
        final int HAUTEUR = 12; // en nb de cases
        final int LARGEUR = 12; // en nb de cases
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
