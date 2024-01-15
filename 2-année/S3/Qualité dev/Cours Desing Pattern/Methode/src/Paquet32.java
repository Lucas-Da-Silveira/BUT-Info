public class Paquet32 extends Paquet {


    public Paquet32(){
        super();
    }

    @Override
    public void remplir() {
        for (Valeur32 valeur : Valeur32.values()) {
            for (Bois couleur : Bois.values()) {
                paquet.add(new Carte(valeur,couleur));
            }
        }
    }
}
