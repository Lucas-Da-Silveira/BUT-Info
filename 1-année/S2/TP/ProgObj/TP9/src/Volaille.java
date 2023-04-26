import java.util.Date;

public abstract class Volaille {

    private int id;
    private double poids;
    private Date dateArrive;
    private double prixParKilo;


    public Volaille(int id, double poids, Date dateArrive, double prixParKilo){
        this.id = id;
        this.poids = poids;
        this.dateArrive = dateArrive;
        this.prixParKilo = prixParKilo;
    }

    public int getId(){
        return id;
    }

    public double getPoids(){
        return poids;
    }

    public Date getDateArrive(){
        return dateArrive;
    }

    public double getPrixParKilo(){
        return prixParKilo;
    }

    public abstract double getPoidsAbattage();

    public double getPrix(){
        return poids * prixParKilo;
    }

    public void modifierPoids(double poids){
        this.poids = poids;
    }
    
}
