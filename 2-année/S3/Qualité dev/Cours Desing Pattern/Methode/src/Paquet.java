import java.util.ArrayList;
import java.util.Collections;

public abstract class Paquet {
    public ArrayList<Carte> paquet;

    public Paquet(){
        paquet = new ArrayList<>();
    }
    public abstract void remplir();
    public void melanger() {
        Collections.shuffle(paquet);
    }
}
