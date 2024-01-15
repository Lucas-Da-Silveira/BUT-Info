public abstract class Jeu {
    public Paquet paquet;
    public Joueur[] joueurs;

    public Jeu(Paquet paquet,Joueur[] joueurs)
    {
        this.paquet = paquet;
        this.joueurs = joueurs;
        System.out.println("initialized"+this.paquet);
    }

    public abstract void distribuerCartes();
    public abstract void melangerPaquet();
    public abstract void remplirPaquet();
    public abstract void jouerManche();
    public abstract boolean isPartieFinie();
    public abstract String getGagnant();
    
    public void jouerPartie(){
        remplirPaquet();
        melangerPaquet();
        distribuerCartes();
        while (!isPartieFinie()){
            jouerManche();
        }
        System.out.println(getGagnant());
    }
}
