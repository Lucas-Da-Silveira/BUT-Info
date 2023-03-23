public class Livre extends Document {

    private String auteur;
    private int nbPages;

    public Livre(int numEnregistrement, String titre, String auteur, int nbPages) {
        super(numEnregistrement, titre);
        this.auteur = auteur;
        this.nbPages = nbPages;
    }
    
    public String toString(){
        return super.toString() + " Auteur: " + auteur + " Nombre de pages: " + nbPages;
    }
}
