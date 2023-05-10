public class Document {
    
    private static int numEnregistrement = 0 ;
    private String titre;

    public Document() {
    }

    public Document(int numEnregistrement, String titre) {
        this.numEnregistrement = numEnregistrement ++;
        this.titre = titre;
    }

    public int getNumEnregistrement() {
        return numEnregistrement;
    }



    public String toString(){
        return "Numéro d'enregistrement: " + numEnregistrement + " Titre: " + titre;
    }

}
