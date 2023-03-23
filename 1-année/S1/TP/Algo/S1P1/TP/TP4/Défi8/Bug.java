/**
 * Created by zulupero on 20/06/16.
 */
public class Bug extends Buggle {
    public Bug(int posx, int posy, int dir) {
        super(posx, posy, dir);
    }
    
    public void enRoute() throws InterruptedException{
        setX(11);
        setVitesse(200);
                final int long_couloir= 5;
        int nb_biscuits = 0;
    
        do{
            nb_biscuits = 0;
            for (int pas=0; pas<long_couloir; pas++){
                avance();
                if(isSurBiscuit())
                    nb_biscuits++;
            }

            if(nb_biscuits >=3){
                gauche();
                message(nb_biscuits +" biscuit ==> à gauche");
                if(isFaceMur()){
                    message("mur ...");
                    droite();
                    if(isFaceMur()){
                        message("encore le mur ...");
                        droite();
                    }
                }
            } else {
                droite();
                message(nb_biscuits +" biscuit ==> à droite");
                if(isFaceMur()){
                    message("mur ...");
                    gauche();
                    if(isFaceMur()){
                        message("encore le mur ...");
                        gauche();
                    }
                }
            }
        }while(!isSortieTrouvee());
        avance();
        message("Sortie !");

    }

    public boolean isSortieTrouvee(){
        return getCouleurSol().equals(StdDraw.YELLOW);

    }
    
}
