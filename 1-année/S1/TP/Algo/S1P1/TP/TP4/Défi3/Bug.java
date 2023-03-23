
public class Bug extends Buggle {
    public Bug(int posx, int posy, int dir) {
        super(posx, posy, dir);
    }

    public void enRoute() {

            int nbPasFaits = 0;

           

            while(isSurBiscuit()==false){
            avance();
            }
            retourne();
            while(isFaceMur()==false){
                
                avance();
            }
            retourne();
        
        
    }
}
