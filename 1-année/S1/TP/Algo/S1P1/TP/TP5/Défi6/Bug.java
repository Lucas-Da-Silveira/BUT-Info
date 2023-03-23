/**
 * Created by zulupero on 20/06/16.
 */
public class Bug extends Buggle {
    public Bug(int posx, int posy, int dir) {
        super(posx, posy, dir);
    }

    public void enRoute() {

        setX(4);
        setY(3);

        setVitesse(50);
        setCouleur(StdDraw.CYAN);
        baisseBrosse();

        int compteur = 0;

        
        do{
            if (isFaceMur()){
                gauche();
                avance();
                compteur += -1;
            }
            if (isFaceMur()){
                droite();
                avance();
                compteur += 1;
            }
            if (compteur == 0){
                avance();
            }
        
            if (isFaceMur()){
                avance();
            }
            


        }while(!isSurBiscuit());

    }
}
