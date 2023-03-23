/**
 * Created by zulupero on 20/06/16.
 */
public class Bug extends Buggle {
    public Bug(int posx, int posy, int dir) {
        super(posx, posy, dir);
    }

    public void enRoute() throws InterruptedException {

        Scanner input = new Scanner(System.in);
        
        String mot;
        
        while (Main.input.hasnext()) {
            mot = Integer.valueOf(Main.input.nextLine());

            mot= input.toLowerCase();
        }
        System.out.println(mot);

    }
    
   
}
