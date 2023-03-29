import java.sql.Array;
import java.util.ArrayList;

public class Responsable extends Employé{
    
    private ArrayList<Employé> employés = new ArrayList<Employé>();

    public Responsable(String nom, String matricule, double indiceSalaire){
        super(nom, matricule, indiceSalaire);
    }

    public ArrayList<Employé> tabHierarchie(){
        return employés;
    }

    public void ajouteEmployé(Employé employé){
        employés.add(employé);
    }

    public void calculeSalaire(double indiceSalaire){
        super.calculeSalaire(indiceSalaire);
        for (Employé employé : employés){
            employé.calculeSalaire(indiceSalaire);
        }
    }

    public String toString(){
        String s = super.toString();
        for (Employé employé : employés){
            s += employé.toString();
        }
        return s;
    }

}
