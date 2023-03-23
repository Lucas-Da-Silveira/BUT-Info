/**
 * Created by zulupero on 20/06/16.
 */
public class Bug extends Buggle {
    public Bug(int posx, int posy, int dir) {
        super(posx, posy, dir);
    }

    //final Scanner input = new Scanner(System.in);
    public void enRoute() throws InterruptedException {
        setVitesse(2);
        int j;
        int i;

        while (Main.input.hasNext()) { // lit une ligne du fichier
            j = Integer.valueOf(Main.input.next()); //x
            i = Integer.valueOf(Main.input.next()); //y
            // calcul à intégrer ici

            if ( j & i=4) {
                setCouleur(StdDraw.BLUE);
                baisseBrosse();
            }
            else{
                leveBrosse();
            }
            


        }   
    }
}
