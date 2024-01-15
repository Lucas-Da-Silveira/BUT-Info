public class Paquet52 extends Paquet{

    public Paquet52(){
        super();
    }
    @Override
    public void remplir() {
        for (Valeur52 valeur : Valeur52.values()) {
            for (Bois couleur : Bois.values()) {
                paquet.add(new Carte(valeur,couleur));
            }
        }
    }
}
