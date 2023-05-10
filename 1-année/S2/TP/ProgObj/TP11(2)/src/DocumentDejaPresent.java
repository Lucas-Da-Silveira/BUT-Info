public class DocumentDejaPresent extends Exception {

    private Document doc;

    public Document getDocument(){
        return doc;
    }

    public String toString(){
        return "Le document " + doc.getNumEnregistrement() + " est déjà présent dans la bibliothèque.";
    }
    
}
