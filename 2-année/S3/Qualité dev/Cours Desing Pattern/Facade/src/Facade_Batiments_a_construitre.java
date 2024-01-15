public class Facade_Batiments_a_construitre {

    /*La méthode doit :
    * créer un batiment
    * lui ajouter un toit et une façade
    * les peindres
    * et le faire acheter à l'achteur
    * */
    public static void construction(Acheteur a, Toiture t, Facade f, String c1, String c2) {
        Batiment b = new Batiment(f, t, 0);
        b.peindre_facade(c1);
        b.peindre_toit(c2);
        FacadeAchat.bail(a, b);
    }
}
