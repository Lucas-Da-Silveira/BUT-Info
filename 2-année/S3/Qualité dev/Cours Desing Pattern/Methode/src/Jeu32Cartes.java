public abstract class Jeu32Cartes extends Jeu{

    Paquet32 paquet = new Paquet32();

    public void distribuerCartes(){
        //à implémenter là ou cela vous semble le plus pertinent
    }

    @Override
    public void jouerManche() {
        //à implémenter là ou cela vous semble le plus pertinent
    }

    public void melangerPaquet(){
        paquet.melanger();
    }

    public void remplirPaquet(){
        paquet.remplir();
    }

    public Jeu32Cartes(Paquet32 paquet,Joueur[] joueurs)
    {
        super(paquet,joueurs);
    }
    public abstract boolean isPartieFinie();
    public abstract String getGagnant();
}
