public class Test {

    public static void main(String[] args) {
        
        Bibliotheque bibliotheque = new Bibliotheque();

        Document document1 = new Document(1,"Tuto comment accepter des cookies");
        Document document2 = new Document(2,"Tuto comment faire un sandwich");
        Document document3 = new Document(3,"Tuto comment faire un sandwich au fromage");


        bibliotheque.ajouterDocument(document1);
        bibliotheque.ajouterDocument(document2);
        bibliotheque.ajouterDocument(document3);

        bibliotheque.afficherDocuments();
        
        bibliotheque.supprimerDocument();

        bibliotheque.afficherDocuments();
    }
}
