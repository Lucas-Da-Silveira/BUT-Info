public abstract class Jeu52Cartes extends Jeu{

    public Paquet52 paquet = new Paquet52();
    public Jeu52Cartes(Paquet52 paquet,Joueur[] joueurs)
    {
        super(paquet,joueurs);
    }

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
    public abstract boolean isPartieFinie();
    public abstract String getGagnant();
}
