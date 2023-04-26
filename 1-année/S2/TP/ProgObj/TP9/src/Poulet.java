import java.util.Date;

public class Poulet extends Volaille{

    private static final double POIDS_ABATTAGE = 2;
    
    public Poulet(int id, double poids, Date dateArrive, double prixParKilo){
        super(id, poids, dateArrive, prixParKilo);
    }

    public double getPoidsAbattage(){
        return POIDS_ABATTAGE;
    }
}
