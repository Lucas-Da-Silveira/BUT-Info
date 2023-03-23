/**
 * Created by zulupero on 20/06/16.
 */
public class Bug extends Buggle {
    public Bug(int posx, int posy, int dir) {
        super(posx, posy, dir);
    }

    public void enRoute() {
        setX(4);
        setY(0);

        setVitesse(20);
        setCouleur(StdDraw.BLUE);
        baisseBrosse();
        


        do{ 
                
                if(isFaceMur()== false){
                    setDirection("N");
                    avance();
                   

                }

                else if (isFaceMur()){
                    droite();
                    avance();
                    gauche();
                    avance();
                
               if (isFaceMur() == true){
                setDirection("S");
                avance();
                avance();
                gauche();
                avance();
                avance();
               }
               if(isFaceMur()== true) {
                setDirection("O");
                avance();
                gauche();
                avance();
                gauche();
                avance();    
               }
               }


            
        
        }while(!isSurBiscuit());

    }
}
