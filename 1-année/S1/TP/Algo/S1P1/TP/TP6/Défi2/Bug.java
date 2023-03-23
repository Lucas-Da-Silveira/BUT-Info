/**
 * Created by zulupero on 20/06/16.
 */
public class Bug extends Buggle {
    public Bug(int posx, int posy, int dir) {
        super(posx, posy, dir);
    }

    public void enRoute() {
        setVitesse(1);
        setCouleur(StdDraw.GRAY); // la couleur de la brosse après passage
        setX(8); setY(0);         // position initale
        baisseBrosse();
        getCouleur(StdDraw.GRAY);
        while(isFaceSnake()) {
            avance();
            droite();
        }
        if (isSurBiscuit()) prendBiscuit();
    }

    /**
     * isFaceSnake
     * @return true si la case devant le bug appartient au serpent
     */
    public boolean isFaceSnake(){
        boolean etatBrosse = isBrosseBaisse();
        boolean result = false;
        if(!isFaceMur()){
            leveBrosse();
            avance();
            
            if (getCouleurSol() == StdDraw.WHITE)
                retourne();
                avance();
                retourne();
            
            if(getCouleurSol() == StdDraw.GREEN)
                if (result = true);
                    if (isBrosseBaisse());
                        avance();
            
        }
        return result;
    
           

    } 
}
