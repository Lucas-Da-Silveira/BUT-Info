/**
 * Created by zulupero on 20/06/16.
 */
public class Bug extends Buggle {
    public Bug(int posx, int posy, int dir) {
        super(posx, posy, dir);
    }

    public void enRoute() throws InterruptedException {
        int a;
        int b;
        int c;
        while (Main.input.hasNext()) { // lit une ligne du fichier
            a = Integer.valueOf(Main.input.next());
            b = Integer.valueOf(Main.input.next());
            c = Integer.valueOf(Main.input.next());
            
            if (a>12 && a<20 || b>12 && b<20 || c>12 && c<20 ) {
                System.out.println("Ado");
            }
            else{
                System.out.println("Pas Ado");
            }
        }

    }
}