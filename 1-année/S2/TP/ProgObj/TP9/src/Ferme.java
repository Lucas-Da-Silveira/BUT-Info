import java.util.ArrayList;
import java.util.Date;
import java.util.Collections;
import java.util.Comparator;


public class Ferme{

    private ArrayList<Volaille> volailles = new ArrayList<Volaille>();
    private double prixJourPoulet;
    private double prixJourCanard;
    private double poidsAbattagePoulet;
    private double poidsAbattageCanard;

    public void ajouterVolaille(Volaille v){
        volailles.add(v);
    }

    public void setPrixJourPoulet(double prix){
        prixJourPoulet = prix; 
    }

    public void setPrixJourCanard(double prix){
        prixJourCanard = prix; 
    }

    public void setPoidsAbattagePoulet(double poids){
        poidsAbattagePoulet = poids; 
    }

    public void setPoidsAbattageCanard(double poids){
        poidsAbattageCanard = poids; 
    }

    public ArrayList<Volaille> trierVolailleAAbattre(){
        ArrayList<Volaille> VolaillesAAbattre = new ArrayList<Volaille>();
        Date aujourdhui = new Date();

        for(Volaille v : volailles){
            if (v instanceof Poulet){
                if (v.getPoids() >= poidsAbattagePoulet && aujourdhui.after(v.getDateArrive())){
                    VolaillesAAbattre.add(v);
                }
            }
            else if (v instanceof Canard){
                if (v.getPoids() >= poidsAbattageCanard && aujourdhui.after(v.getDateArrive())){
                    VolaillesAAbattre.add(v);
                }
            }
        }

        Collections.sort(VolaillesAAbattre, new Comparator<Volaille>() {
            public int compare(Volaille v1, Volaille v2){
                return Double.compare(v1.getPoids(), v2.getPoids());
            }
        });
        return VolaillesAAbattre;
    }

    public double  evaluerPrixVolaillesAAbattre(){
        double prixTotal = 0;

        for (Volaille v : trierVolailleAAbattre()){
            if (v instanceof Poulet){
                prixTotal += v.getPoids() * prixJourPoulet;
            }
            else if (v instanceof Canard){
                prixTotal += v.getPoids() * prixJourCanard;
            }
        }
        return prixTotal;
    }

}