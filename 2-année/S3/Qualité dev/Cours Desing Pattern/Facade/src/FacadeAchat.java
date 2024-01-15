public class FacadeAchat {


    /*La méthode doit :
    * vérifier que l'acheteur à les moyens de payer
    * retirer l'argent à l'acheteur
    * ajouter le batiment au sein de l'objet acheteur
    * si l'acheteur n'a pas les moyens de payer afficher un message le précisant
    * */
    public static void bail(Acheteur a, Batiment b){
        if(a.getArgent()>=b.getPrix()){
            a.editArgent(-b.getPrix());
            a.setMaison(b);
        }
        else{
            System.out.println("L'acheteur n'a pas les moyens de payer");
        }
    }
}
