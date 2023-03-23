import java.awt.*;

public class Bug extends Buggle {
    public Bug(int posx, int posy, int dir) {
        super(posx, posy, dir);
    }

    public void enRoute() throws InterruptedException {
        setX(13);
        setY(1);

        setVitesse(100);

        final int tours_a_faire = 7;
        final int nb_cote_par_tour = 4;
                final int debut = 255;
                final int fin = 128;
        
        Color couleur_sol;
        int coef_couleur;

         baisseBrosse();
        for(int tour=0; tour < tours_a_faire; tour++){
            coef_couleur = (int)(debut-(1.0*fin/debut)*tour);
            couleur_sol = new Color(205,coef_couleur,205);
            setCouleur(couleur_sol);
            for (int cote=0; cote<nb_cote_par_tour ; cote++){
                for(int pas=0; pas< 4; pas++)
                    avance();
                gauche();
                for(int pas=0; pas< 2; pas++)
                    avance();
                droite();
                for(int pas=0; pas< 4; pas++)
                    avance();
                droite();
                for(int pas=0; pas< 2; pas++)
                    avance();
                gauche();
                for(int pas=0; pas< 4; pas++)
                    avance();
                gauche();
            }
            message("tour : "+(tour+1));
        }

    }
}
