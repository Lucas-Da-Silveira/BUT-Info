import java.util.ArrayList;;

public class Paquet {

    ArrayList<String> paquet;
    int nbCartes = 32;

    public Paquet() {
        paquet = new ArrayList<String>();
        for (int i = 0; i < nbCartes; i++) {
            paquet.add("carte" + i);
        }
    }

    public void melanger(){
        for (int i = 0; i < nbCartes; i++){
            int random = (int) (Math.random() * nbCartes);
            String temp = paquet.get(i);
            paquet.set(i, paquet.get(random));
            paquet.set(random, temp);
        }
    }

    public String tirerCarte(){
        String carte = paquet.get(0);
        paquet.remove(0);
        return carte;
    }

    public void ajouterCarte(String carte){
        paquet.add(carte);
    }

    public int getNbCartes(){
        return paquet.size();
    }

    public String toString(){
        String str = "";
        for (int i = 0; i < nbCartes; i++){
            str += paquet.get(i) + "\n";
        }
        return str;
    }
    
}
