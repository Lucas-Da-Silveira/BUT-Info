/**
 * Created by zulupero on 20/06/16.
 */
public class Bug extends Buggle {
    public Bug(int posx, int posy, int dir) {
        super(posx, posy, dir);
    }

    public void enRoute() {
        setVitesse(10);
        setCouleur(StdDraw.ORANGE);
        setX(0);
        setX(2); setY(6); dessineLosange();
        setX(7); setY(6); dessineLosange();
        setX(5); setY(3); dessineLosange();
        setX(0); setY(0);
    }

    public void dessineLosange(){

       
       
    }
}
