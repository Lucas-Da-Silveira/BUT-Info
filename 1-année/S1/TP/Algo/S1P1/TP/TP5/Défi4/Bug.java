import java.util.*;
/**
 * Created by zulupero on 20/06/16.
 */
public class Bug extends Buggle {
    public Bug(int posx, int posy, int dir) {
        super(posx, posy, dir);
    }

    public void enRoute() {
        setX(0);
        setY(0);
        setVitesse(50);

        do{
            
            if (isFaceMur() == false){
                avance();
                gauche();
            }
            else if (isFaceMur()){
                droite();
            }
        }while(!isSurBiscuit());
    }
}
