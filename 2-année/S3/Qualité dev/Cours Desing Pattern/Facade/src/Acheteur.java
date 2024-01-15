public class Acheteur {

    private int argent;
    private Batiment maison;
    private String nom;

    Acheteur(int argent, String nom){
        this.argent=argent;
        this.nom=nom;
    }

    public int getArgent(){
        return(this.argent);
    }

    public Batiment getMaison(){
        return(this.maison);
    }

    public void setMaison(Batiment maison){
        this.maison=maison;
    }

    public void editArgent(int val){
        this.argent=argent+val;
    }

    public void peindre_toit(String color){
        if(this.argent>250){
            this.maison.getToit().tp.peindre(color);
        }
        System.out.println("changement non effectué pour cause de manque de fond");
    }

    public void peindre_facade(String color){
        if(this.argent>250){
            this.maison.getDevanture().fp.peindre(color);
        }
        System.out.println("changement non effectué pour cause de manque de fond");
    }

    public String toString(){
        String colorToit = this.maison.getToit().getCouleur();
        String colorFacade = this.maison.getDevanture().getCouleur();
        int nbfen = this.maison.getDevanture().getNb_fenetre();
        int tailleTuiles = this.maison.getToit().getTaille_tuiles();
        return(this.nom + " possède une maison de couleur " + colorFacade + " avec " + nbfen + " fenêtres et un toit de couleur "
        + colorToit + " avec des tuiles de tailles " + tailleTuiles + ".");
    }
}
