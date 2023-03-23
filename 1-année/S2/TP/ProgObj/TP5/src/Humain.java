public class Humain {
    protected String nom;
    protected String boisson;

    public Humain(String nom, String string) {
        this.nom = nom;
        this.boisson = "eau";
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getBoisson() {
        return boisson;
    }

    public void setBoisson(String boisson) {
        this.boisson = boisson;
    }

    public void parle(String texte) {
        System.out.println("(" + nom + ") - " + texte);
    }

    public String sePresenter() {
        parle("Bonjour, je suis " + nom + " et j'aime boire du " + boisson + ".");
        return boisson;
    }
}