public class Cartes extends Paquet{

    protected String[] Valeur;
    protected String[] Couleur;

    public Cartes(){
        Valeur = new String[]{"As","Roi","Dame","Valet","10","9","7","8"};
        Couleur = new String[]{"Pique","Coeur","Carreau","Trèfle"};

        for (int i = 0; i < Valeur.length; i++){
            for (int j = 0; j < Couleur.length; j++){
                paquet.add(Valeur[i] + " de " + Couleur[j]);
            }
        }
    }

    public String toString(){
        String str = "";
        for (int i = 0; i < paquet.size(); i++){
            str += paquet.get(i) + "\n";
        }
        return str;
    }
}