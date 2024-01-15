public class Facade {
    private String couleur;
    private int nb_fenetre;


    public Facade(String couleur, int nb_fenetre){
        this.couleur=couleur;
        this.nb_fenetre=nb_fenetre;
    }

    Peinture fp = (String color) -> {
        this.couleur=color;
        return(500);
    };

    public String getCouleur() {
        return(this.couleur);
    }

    public int getNb_fenetre() {
        return(this.nb_fenetre);
    }
}
