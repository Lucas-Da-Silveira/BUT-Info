import java.util.ArrayList;

public class Entreprise extends Employé {

    public Entreprise(String nom, String matricule, double indiceSalaire) {
        super(nom, matricule, indiceSalaire);
    }

    public void afficheOrdreAlphabétique(){
        ArrayList<Employé> employés = new ArrayList<Employé>();
        for (Employé employé : employés){
            System.out.println(employé.getNom());
        }
    }

    public void afficheOrdreIndiceSalaire(){
        ArrayList<Employé> employés = new ArrayList<Employé>();
        for (Employé employé : employés){
            System.out.println(employé.getIndiceSalaire());
        }
    }
    
    public void addEmployé(){
        ArrayList<Employé> employés = new ArrayList<Employé>();
        employés.add(new Employé("Jean", "123", 1.5));
    }

    public void addResponsable(){
        ArrayList<Employé> employés = new ArrayList<Employé>();
        employés.add(new Responsable("Patrick", "452", 2.3));
    }

    public void afficheHierarchie(){
        ArrayList<Employé> employés = new ArrayList<Employé>();
        for (Employé employé : employés){
            System.out.println(employé.toString());
        }
    }

    public void rechercheEmployé(){
        ArrayList<Employé> employés = new ArrayList<Employé>();
        for (Employé employé : employés){
            if (employé.getNom().equals(employé.getNom())){
                System.out.println(employé.toString());
            }
        }
    }
}
