public class Brigand extends Humain {
    private String look;
    private int nbDamesEnlevees;
    private double prime;

    public Brigand(String nom, String look, int nbDamesEnlevees, double prime) {
        super(nom, "tord-boyaux");
        this.look = look;
        this.nbDamesEnlevees = nbDamesEnlevees;
        this.prime = prime;
    }

    @Override
    public String getNom() {
        return this.nom + " le méchant";
    }

    @Override
    public String sePresenter() {
        String presentation = super.sePresenter();
        presentation += "\n(J'ai l'air " + this.look + " et j'ai déjà kidnappé " + this.nbDamesEnlevees + " dames !)";
        presentation += "\n(Ma tête est mise à prix " + this.prime + "$ !)";
        return presentation;
    }

    public void kidnapper(Dame dame) {
        if (dame.estLibre()) {
            dame.kidnapper();
            this.nbDamesEnlevees++;
            this.prime += 1000;
            System.out.println("(" + this.nom + ") - A l'aide ! On me kidnappe !");
        } else {
            System.out.println("(" + this.nom + ") - Je suis déjà prisonnier !");
        }
    }

    public int getNbDamesEnlevees() {
        return nbDamesEnlevees;
    }

    

    public String getLook() {
        return look;
    }

}
