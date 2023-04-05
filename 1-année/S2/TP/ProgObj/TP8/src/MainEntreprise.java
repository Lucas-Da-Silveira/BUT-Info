public class MainEntreprise {
    
    public static void main(String[] args) {

        Responsable r1 = new Responsable("Directeur Général","111", 5 );
        Responsable r2 = new Responsable("Directeur Commercial","220", 3.5 );
        Responsable r3 = new Responsable("Directeur Technique","330", 4 );
        
        Commerciaux c1 = new Commerciaux("Commercial 1", "221", 2, 1500, 2);
        Commerciaux c2 = new Commerciaux("Commercial 2", "222", 2, 2000, 2);
        Commerciaux c3 = new Commerciaux("Commercial 3", "223", 2, 2500, 2);

        Employé t1 = new Employé("Technicien 1", "331", 1.5);
        Employé t2 = new Employé("Technicien 2", "332", 1.5);
        Employé t3 = new Employé("Technicien 3", "333", 1.5);

        r1.ajouteEmployé(c1);

        r2.ajouteEmployé(c2);

        r3.ajouteEmployé(c3);

        r1.ajouteEmployé(t1);

        r2.ajouteEmployé(t2);

        r3.ajouteEmployé(t3);

        Entreprise e1 = new Entreprise("Entreprise 1", "000", 1);



        e1.afficheHierarchie();
    }
}
