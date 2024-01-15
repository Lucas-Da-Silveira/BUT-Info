public class Assemblage {

    public static void main(String[] args) {

        Acheteur cecile = new Acheteur(1500, "Cécile");
        Acheteur simon = new Acheteur(2500, "Simon");

        Toiture toit_monopente = new Toiture("bleu", 5);
        Toiture toit_papillon = new Toiture("rouge", 8);
        Toiture toit_mansart = new Toiture("brun", 4);

        Facade facade_pavillon = new Facade("beige", 4);
        Facade facade_de_manoir = new Facade("noir", 14);
        Facade facade_méditeranéenne = new Facade("bleu", 6);

        Batiment manoir = new Batiment(facade_de_manoir, toit_mansart, 3200);
        Batiment pavillon = new Batiment(facade_pavillon, toit_papillon, 600);

        FacadeAchat.bail(cecile, pavillon);
        System.out.println(cecile);
        FacadeAchat.bail(simon, manoir);

        Facade_Batiments_a_construitre.construction(simon, toit_monopente, facade_méditeranéenne, "rouge", "rouge");
        System.out.println(simon);
    }

}
