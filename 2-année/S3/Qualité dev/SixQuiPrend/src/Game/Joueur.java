package Game;

import java.util.ArrayList;
import java.util.List;

public class Joueur {

    private final List<Carte> main = new ArrayList<>();

    public List<Carte> getMain() {
        return main;
    }

    public void ajouterCarteMain(Carte carte) {
        main.add(carte);
    }

    public void choisirCarte() {
        if (!main.isEmpty()) {
            Carte carteChoisie = main.get(0);
            main.remove(carteChoisie);
        }
    }

    public void jouerCarte(Joueur adversaire, Carte carte) {
        adversaire.recevoirCarte(carte);
    }

    public void recevoirCarte(Carte carte) {
        main.add(carte);
    }
}
