import java.awt.*;

public class Bug extends Buggle {
    public Bug(int posx, int posy, int dir) {
        super(posx, posy, dir);
    }

    public void enRoute() throws InterruptedException {
        setX(9);
        setY(1);

        setVitesse(1000);

        final int tours_a_faire = 10;
        final int pas_cote = 8;
                final int debut = 200;
                final int fin = 128;

        Color couleur_sol;
        int coef_couleur;

        baisseBrosse();
        for(int tour=0; tour < tours_a_faire; tour++){
            coef_couleur = (int)(debut-(11.0*fin/debut)*tour);
            couleur_sol = new Color(coef_couleur,coef_couleur,coef_couleur);
            setCouleur(couleur_sol);
            for (int cote=0; cote<4 ; cote++){
                for(int pas=0; pas< pas_cote; pas++)
                    avance();
                gauche();
            }
            message("tour : "+(tour+1));
        }


    }
}
