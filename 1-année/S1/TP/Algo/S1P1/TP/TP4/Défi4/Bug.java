/**
 * Created by zulupero on 20/06/16.
 */
public class Bug extends Buggle {
    public Bug(int posx, int posy, int dir) {
        super(posx, posy, dir);
    }

    public void enRoute() {
        final int nb_biscuit_a_prendre = 4;

        setVitesse(8);

        int nb_pas = 0;
        int nb_biscuit = 0;

        while((nb_biscuit < nb_biscuit_a_prendre )){
            if (isSurBiscuit()){
                prendBiscuit();
                nb_biscuit++;
            }

            if(nb_biscuit == nb_biscuit_a_prendre){
                break;
            }
            if(!isFaceMur()){
                avance();
                nb_pas++;
            }
            else{
                break;
            }
            
        }
        retourne();
        for (int k=0; k<nb_pas; k++)
            avance();

    }
}
