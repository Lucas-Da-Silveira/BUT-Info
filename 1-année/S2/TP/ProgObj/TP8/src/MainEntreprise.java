public class MainEntreprise {
    
    public static void main(String[] args) {
        
        Responsable r1 = new Responsable("Directeur Général","111", 5 );
        Responsable r2 = new Responsable("Directeur Commercial","220", 3.5 );
        Responsable r3 = new Responsable("Directeur Technique","330", 4 );
        
        Commerciaux c1 = new Commerciaux("Commercial 1", "221", 2, 1500, 2);
        Commerciaux c2 = new Commerciaux("Commercial 2", "222", 2, 2000, 2);
        Commerciaux c3 = new Commerciaux("Commercial 3", "223", 2, 2500, 2);
    }
}
