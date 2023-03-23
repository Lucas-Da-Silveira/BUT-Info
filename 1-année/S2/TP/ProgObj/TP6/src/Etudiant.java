public class Etudiant extends Personne {

    private String numeroEtudiant;
    private String fac;

    public void Etudiant(){
    }
    
    public void Etudiant(Etudiant e){

    }

    public void Etudiant(String unNom, int unAge, String unNumeroEtudiant, String uneFac){
        super.Personne(unNom, unAge);
        this.numeroEtudiant = unNumeroEtudiant;
        this.fac = uneFac;
    }

    public boolean equals(Object o){
        if (o instanceof Etudiant){
            Etudiant e = (Etudiant) o;
            return (super.equals(e) && this.numeroEtudiant.equals(e.numeroEtudiant) && this.fac.equals(e.fac));
        } 
        else {
            return false;
        }
    }

    public String getFaculte(){
        return this.fac;
    }

    public void setFaculte(String uneFac){
        this.fac = uneFac;
    }

    public String getNumeroEtudiant(){
        return this.numeroEtudiant;
    }

    public void setNumeroEtudiant(String unNumeroEtudiant){
        this.numeroEtudiant = unNumeroEtudiant;
    }

    public void init(){
        super.init();
        this.numeroEtudiant = "Inconnu";
        this.fac = "Inconnu";
    }

    public String toString(){
        return super.toString() + " Numero d'etudiant : " + this.numeroEtudiant + " Faculte : " + this.fac;
    }
}
