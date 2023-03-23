public class Cowboy extends Humain {
    private String adjectif;
    private int popularite;

    public Cowboy(String nom, String adjectif, int popularite) {
        super(nom, "whisky");
        this.adjectif = adjectif;
        this.popularite = popularite;
    }

    public String getAdjectif() {
        return adjectif;
    }

    public int getPopularite() {
        return popularite;
    }

    public void liberer(Dame dame) {
        if (!dame.estLibre()) {
            dame.liberer();
            this.popularite--;
            System.out.println("(" + this.nom + ") - Merci beaucoup pour votre aide !");
        } else {
            System.out.println("(" + this.nom + ") - Je ne suis pas prisonnier !");
        }
    }

    @Override
    public String sePresenter() {
        String presentation = super.sePresenter();
        presentation += "\n(J'ai l'air " + this.adjectif + " et j'ai une popularité de " + this.popularite + " !)";
        return presentation;
    }

}
