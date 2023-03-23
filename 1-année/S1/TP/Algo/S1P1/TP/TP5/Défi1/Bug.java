import java.util.*;

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
        
        do{
            if(isFaceMur()){
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
        

        }while(!isSurBiscuit());
        message("Zebi enfin!");
        

     }
}
