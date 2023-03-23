import java.util.*;
import static java.lang.System.exit;

public class Bug extends Buggle {
    public Bug(int posx, int posy, int dir) {
        super(posx, posy, dir);
    }

    public void enRoute() {
        setVitesse(1000);
        final int nbpas= 5;
        int max = 2;
        int min = 0;
        int range = max - min + 1;
        int execution = 0;
        int mur = 0;
        
        do{
            if(isFaceMur()){
                mur++;
                if((int)(Math.random() * range) + min == 0){
                    avance();
                }
                else if ((int)(Math.random() * range) + min == 1){
                    droite();
                }
                else if((int)(Math.random() * range) + min== 2){
                    gauche();
                }
            
            }else{
                if((int)(Math.random() * range) + min == 0){
                    avance();
                }
                else if ((int)(Math.random() * range) + min == 1){
                    droite();
                }
                else if((int)(Math.random() * range) + min== 2){
                    gauche();
                }
            }

        if(mur >= 1000){
            message("Zebi le mur !");
            System.exit(0);
        }

        }while(!isSurBiscuit());
        message("Zebi enfin!");
        

     }
}
