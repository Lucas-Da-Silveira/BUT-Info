public class DocumentDejaPresent extends Exception {

    private Document document;

    public DocumentDejaPresent(Document document) {
        this.document = document;
    }

    public Document getDocument() {
        return document;
    }

    @Override
    public String toString() {
        return "Le document " + document.toString() + " est déjà présent dans la médiathèque.";
    }
    
}
