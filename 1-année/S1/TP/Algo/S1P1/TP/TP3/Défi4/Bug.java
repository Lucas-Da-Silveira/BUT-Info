/**
 * Created by zulupero on 20/06/16.
 */
public class Bug extends Buggle {
    public Bug(int posx, int posy, int dir) {
        super(posx, posy, dir);
    }

    public void enRoute() throws InterruptedException {
            double op1, op2; // les opérandes
            char operateur;  // l'opérateur
            double resultat = 0.0; // pour ranger le résultat
            
            while (Main.input.hasNext()) { // lit une ligne du fichier
                op1 = Double.valueOf(Main.input.next());
                operateur = Main.input.next().charAt(0);
                op2 = Double.valueOf(Main.input.next());
                /* votre code ici*/
                if (operateur == '+'){
                    resultat = op1 + op2;
                    resultat = operateur;
                    System.out.println("Le résultat est =" +resultat);
                }
                else if (operateur =='-'){
                    resultat = op1 - op2;
                    resultat = operateur;
                    System.out.println("Le résultat est =" +resultat);
                }
                else if (operateur =='/')
                    resultat = op1/op2;
                    resultat = operateur;
                    System.out.println("Le résultat est =" +resultat);
                
                
                
   
            }
    }
}
