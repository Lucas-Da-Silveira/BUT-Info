import java.util.*;
import static java.lang.System.exit;

public class Bug extends Buggle {
    public Bug(int posx, int posy, int dir) {
        super(posx, posy, dir);
    }

    public void enRoute() {
        setVitesse(1000);
        int max = 2;
        int min = 0;
        int range = max - min + 1;
        int execution = 0;
        
        do{
            if(isFaceMur()){
                droite();
            }else{
                if((int)(Math.random() * range) + min == 0){
                    avance();
                    execution++;
                }
                else if ((int)(Math.random() * range) + min == 1){
                    droite();
                    execution++;
                }
                else if((int)(Math.random() * range) + min== 2){
                    gauche();
                    execution++;
                }
            }

        if(execution >= 3000){
            message("Zebi c'est fini !");
            System.exit(0);
        }

        }while(!isSurBiscuit());
        message("Zebi enfin!");
        

     }
}
