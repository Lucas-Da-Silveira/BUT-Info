public class Toiture {
    private String couleur;
    private int taille_tuiles;

    public Toiture(String couleur, int taille_tuiles){
        this.couleur=couleur;
        this.taille_tuiles=taille_tuiles;
    }

    public Peinture tp = (String color) -> {
        this.couleur=color;
        return(250);
    };

    public String getCouleur() {
        return(this.couleur);
    }

    public int getTaille_tuiles() {
        return(this.taille_tuiles);
    }
}
