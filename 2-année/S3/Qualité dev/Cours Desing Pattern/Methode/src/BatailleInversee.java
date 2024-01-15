import java.util.ArrayList;
import java.util.Random;

public class BatailleInversee extends Jeu52Cartes{

    ArrayList<Carte> temp = new ArrayList<>();
    public int gagnant;
    public BatailleInversee(Paquet52 paquet, Joueur[] joueurs) {
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
        if (joueurs[0].main.peek().v52.getForce() < joueurs[1].main.peek().v52.getForce()) {
            joueurs[0].main.add(joueurs[1].main.poll());
            joueurs[0].main.add(joueurs[0].main.poll());
            if (temp.size() > 0) {
                for (Carte i : temp) {
                    joueurs[0].main.add(i);
                }
                temp.clear();
            }
        } else if (joueurs[0].main.peek().v52.getForce() == joueurs[1].main.peek().v52.getForce()) {
            temp.add(joueurs[0].main.poll());
            temp.add(joueurs[1].main.poll());
            temp.add(joueurs[0].main.poll());
            temp.add(joueurs[1].main.poll());
        } else {
            joueurs[1].main.add(joueurs[0].main.poll());
            joueurs[1].main.add(joueurs[1].main.poll());
            if (temp.size() > 0) {
                for (Carte i : temp) {
                    joueurs[1].main.add(i);
                }
                temp.clear();
            }
        }
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