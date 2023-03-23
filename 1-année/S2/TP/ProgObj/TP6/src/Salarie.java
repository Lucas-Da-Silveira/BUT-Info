public class Salarie extends Personne{

    private String numeroSecu;
    private String employeur;

    public Salarie(Salarie salarie) {
    }

    public void Salarie(){
    }

    public void Salarie(Salarie s){
        super.Personne(s);
        this.numeroSecu = s.numeroSecu;
        this.employeur = s.employeur;
    }

    public void Salarie(String unNom, int unAge, String unNumeroSecu, String unEmployeur){
        super.Personne(unNom, unAge);
        this.numeroSecu = unNumeroSecu;
        this.employeur = unEmployeur;
    }

    public boolean equals(Object o){
        if (o instanceof Salarie){
            Salarie s = (Salarie) o;
            return (super.equals(s) && this.numeroSecu.equals(s.numeroSecu) && this.employeur.equals(s.employeur));
        } 
        else {
            return false;
        }
    }

    public String getEmployeur(){
        return this.employeur;
    }

    public void setEmployeur(String unEmployeur){
        this.employeur = unEmployeur;
    }

    public String getNumeroSecu(){
        return this.numeroSecu;
    }

    public void setNumeroSecu(String unNumeroSecu){
        this.numeroSecu = unNumeroSecu;
    }

    public void init(){
        super.init();
        this.numeroSecu = "Inconnu";
        this.employeur = "Inconnu";
    }

    public String toString(){
        return super.toString() + " Numero de securite sociale : " + this.numeroSecu + " Employeur : " + this.employeur;
    }
    
}
