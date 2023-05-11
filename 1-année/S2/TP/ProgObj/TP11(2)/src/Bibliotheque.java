public class Bibliotheque {
    private String nomBibliotheque;
    private Document [ ] listeDocs; 
    private int prochainNumero; 
    private static final int CAPACITE = 100; 


    public Document getDocument(int num) throws Exception{
        for (Document doc : listeDocs){
            if (doc != null && doc.getNumEnregistrement() == num){
                return doc;
            }
        }
        throw new Exception("Le document numéro " +num +" n'est pas dans la bibliothèque");
    }

    public Document retirerDocument(int num) throws Exception{
        for(int i = 0; i < listeDocs.length; i++){
            Document doc = listeDocs[i];
            if (doc != null && doc.getNumEnregistrement() == num){
                listeDocs[i] = null;
                return doc;
            }
        }
        throw new Exception("Le document numéro " +num+ " n'est pas dans la bibliothèque");
    }

    public void ajouterDocument(Document d) throws DocumentDejaPresent, Exception {
        for (Document doc : listeDocs) {
            if (doc != null && doc.equals(d)) {
                throw new DocumentDejaPresent(doc);
            }
        }

        if (prochainNumero >= CAPACITE) {
            throw new Exception("La bibliothèque est déjà remplie. Impossible d'ajouter un nouveau document.");
        }

        listeDocs[prochainNumero] = d;
        prochainNumero++;
    }







    public static void main(String[] args) {
        Bibliotheque bibli = new Bibliotheque();

        // try {
        //     Document document = bibli.getDocument(123); 
        // } 
        // catch (Exception e) {
        //     System.out.println(e.getMessage());
        // }

        // try {
        //     Document document = bibli.retirerDocument(123);
        // } 
        // catch (Exception e) {
        //     System.out.println(e.getMessage());
        // }

        // try {
        //     Document document = new Document(); 
        //     bibli.ajouterDocument(document);
        // } 
        // catch (DocumentDejaPresent e) {
        //     Document documentexistant = e.getDocument();
        //     System.out.println(e.toString());
        // } 
        // catch (Exception e) {
        //     System.out.println(e.getMessage());
        // }

        try {
            while (true) {
                Document document = new Document();
                bibli.ajouterDocument(document);
            }
        } 
        catch (DocumentDejaPresent e) {
            Document documentexistant = e.getDocument();
            System.out.println(e.toString());

        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
