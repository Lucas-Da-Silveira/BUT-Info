import java.util.ArrayList;

public class Responsable extends Employé{
    
    private ArrayList<Employé> employés = new ArrayList<Employé>();

    public Responsable(String nom, String matricule, double indiceSalaire){
        super(nom, matricule, indiceSalaire);
    }
}
