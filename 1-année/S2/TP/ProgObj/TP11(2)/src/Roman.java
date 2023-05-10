public class Roman extends Livre {

    private String prixLitteraire;

    public Roman(int numEnregistrement, String titre, String auteur, int nbPages, String prixLitteraire) {
        super(numEnregistrement, titre, auteur, nbPages);
        this.prixLitteraire = prixLitteraire;
    }

    public String toString(){
        return super.toString() + " Prix littéraire: " + prixLitteraire;
    }
    
}
