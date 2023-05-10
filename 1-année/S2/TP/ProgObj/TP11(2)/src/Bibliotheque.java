public class Bibliotheque {
    
    private String [] bibliotheque;

    public Bibliotheque(){
        this.bibliotheque = new String[5];
    }

    public Bibliotheque(String[] bibliotheque) {
        this.bibliotheque = bibliotheque;
    }

    public String[] getBibliotheque() {
        return bibliotheque;
    }

    public void setBibliotheque(String[] bibliotheque) {
        this.bibliotheque = bibliotheque;
    }

    public void ajouterDocument(Document document){
        for (int i = 0; i < bibliotheque.length; i++){
            if (bibliotheque[i] == null){
                bibliotheque[i] = document.toString();
                break;
            }
        }
    }

    public void afficherDocuments(){
        for (int i = 0; i < bibliotheque.length; i++){
            if (bibliotheque[i] != null)
                System.out.println(bibliotheque[i]);
        }
    }

    public void supprimerDocument(){
        for (int i = 0; i < bibliotheque.length; i++){
            if (bibliotheque[i] != null){
                bibliotheque[i] = null;
                break;
            }
        }
    }

    public boolean afficheiemeDocument(){
        for (int i = 0; i < bibliotheque.length; i++){
            if (bibliotheque[i] != null){
                System.out.println(bibliotheque[i]);
                break;
            }
            else{
                return false;
            }
        }   
        return true;
    }

}
