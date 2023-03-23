public class Dame extends Humain {
    private String couleurRobe;
    private boolean estLibre;


    public Dame(String nom) {
        super(nom, nom);
        this.boisson = "lait";
        this.couleurRobe = "rose";
        this.estLibre = true;
    }

    public String getCouleurRobe() {
        return couleurRobe;
    }

    public void setCouleurRobe(String couleurRobe) {
        this.couleurRobe = couleurRobe;
    }

    public boolean estLibre() {
        return estLibre;
    }

    public void kidnapper() {
        if (estLibre) {
            parle("A l'aide ! On me kidnappe !");
            estLibre = false;
        } else {
            parle("Je suis déjà prisonnière !");
        }
    }

    public void liberer() {
        if (!estLibre) {
            parle("Merci beaucoup pour votre aide !");
            estLibre = true;
        } else {
            parle("Je ne suis pas prisonnière !");
        }
    }

    public void changerRobe(String couleur) {
        couleurRobe = couleur;
        parle("Regardez ma nouvelle robe " + couleurRobe + " !");
    }

    @Override
    public String sePresenter() {
        parle("Bonjour, je suis Miss " + nom + ", ma robe est de couleur " + couleurRobe + " et j'aime boire du " + boisson + ".");
        return couleurRobe;
    }
}