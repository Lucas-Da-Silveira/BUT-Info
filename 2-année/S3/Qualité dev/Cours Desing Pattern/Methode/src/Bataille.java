import java.util.ArrayList;
import java.util.Random;

public class Bataille extends Jeu32Cartes{

    ArrayList<Carte> temp = new ArrayList<>();
    public int gagnant;
    public Bataille(Paquet32 paquet, Joueur[] joueurs) {
        super(paquet, joueurs);
    }

    public void distribuerCartes(){
        //à implémenter là ou cela vous semble le plus pertinent
        int i=0;
        while(!paquet.paquet.isEmpty())
        {
            Carte c = paquet.paquet.remove(0);
            joueurs[i].main.add(c);
            i=(i+1)% joueurs.length;
        }
    }

    @Override
    public void jouerManche() {
        //à implémenter là ou cela vous semble le plus pertinent
    }

    @Override
    public boolean isPartieFinie() {

        if (joueurs[0].main.isEmpty()) {
            gagnant = 1;
            return true;
        } else if (joueurs[1].main.isEmpty()) {
            gagnant = 0;
            return true;
        }
        return false;
    }

    @Override
    public String getGagnant() {
        return joueurs[gagnant].nom;
    }
}