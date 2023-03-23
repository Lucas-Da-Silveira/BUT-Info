/**
 * Created by zulupero on 20/06/16.
 */
public class Bug extends Buggle {
    public Bug(int posx, int posy, int dir) {
        super(posx, posy, dir);
    }

    public void enRoute() throws InterruptedException {
        final int nb_biscuit_a_prendre = 4;
        final int vie_depart = 9;
        setVitesse(6);
        int nb_pas = 0;
        int nb_biscuit = 0;
        int vie = vie_depart;

        while((nb_biscuit < nb_biscuit_a_prendre ) && !isFaceMur() && vie>0){
            if (isSurBiscuit()){
                prendBiscuit();
                nb_biscuit++;
                if(getCouleurSol() == StdDraw.GREEN)
                    vie -=2;
            } else if( getCouleurSol().equals(StdDraw.GREEN) )
                vie--;
                        avance();
                        nb_pas++;
        }

        if(vie>0) {
            retourne();
            for (int k=0; k<nb_pas; k++)
                avance();
        } else {
            baisseBrosse();
            setCouleur(StdDraw.BLACK);
            droite();

        }
    }
}
