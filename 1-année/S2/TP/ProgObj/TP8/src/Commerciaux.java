public class Commerciaux extends Employé {

    private double vente;
    private double intéressement;

    public Commerciaux(String nom, String matricule, double indiceSalaire, double vente, double intéressement){
        super(nom, matricule, indiceSalaire);
        this.vente = vente;
        this.intéressement = intéressement;
    }

    public double getVente(){
        return vente;
    }

    public double getIntéressement(){
        return intéressement;
    }

    public void setVente(double vente){
        this.vente = vente;
    }

    public void setIntéressement(double intéressement){
        this.intéressement = intéressement;
    }

    public void calculeSalaire(double indiceSalaire, double vente, double intéressement){
        super.calculeSalaire(indiceSalaire);
        this.salaire += vente * intéressement;
    }

}
