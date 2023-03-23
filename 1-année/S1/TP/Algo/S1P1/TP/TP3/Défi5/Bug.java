/**
 * Created by zulupero on 20/06/16.
 */
public class Bug extends Buggle {
    public Bug(int posx, int posy, int dir) {
        super(posx, posy, dir);
    }

    public void enRoute() throws InterruptedException{
        int annee;

        while (Main.input.hasNext()) {
            annee = Integer.valueOf(Main.input.next());

            if (annee % 4 == 0 && annee % 100 != 0|| annee % 400 == 0 && annee % 100 !=0){
                System.out.println(annee +" est une année bisextile");
            }
            else {
                System.out.println(annee +" n'est pas une année bisextile");
            }
           
         }
            
            
        
    }

}
