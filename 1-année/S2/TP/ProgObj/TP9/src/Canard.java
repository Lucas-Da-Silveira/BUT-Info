import java.util.Date;

public class Canard extends Volaille{
    
    private static final double POIDS_ABATTAGE = 4.75;
    
    public Canard(int id, double poids, Date dateArrive, double prixParKilo){
        super(id, poids, dateArrive, prixParKilo);
    }

    public double getPoidsAbattage(){
        return POIDS_ABATTAGE;
    }


}
