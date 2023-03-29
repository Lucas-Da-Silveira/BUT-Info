public class Employé {

    private String nom;
    private String matricule;
    private double indiceSalaire;
    protected double salaire;
    

    public Employé(String nom, String matricule, double indiceSalaire) {
        this.nom = nom;
        this.matricule = matricule;
        this.indiceSalaire = indiceSalaire;
    }

    public String getNom() {
        return nom;
    }

    public String getMatricule(){
        return matricule;
    }

    public double getIndiceSalaire(){
        return indiceSalaire;
    }

    public double getSalaire(){
        return salaire;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setMatricule(String matricule){
        this.matricule = matricule;
    }

    public void setIndiceSalaire(double indiceSalaire){
        this.indiceSalaire = indiceSalaire;
    }

    public void setSalaire(double salaire){
        this.salaire = salaire;
    }

    public void calculeSalaire(double indiceSalaire){
        this.salaire = indiceSalaire * salaire;
    }

    public String toString(){
        return "Nom: " + nom + " Matricule: " + matricule + " Salaire: " + salaire;
    }
}
