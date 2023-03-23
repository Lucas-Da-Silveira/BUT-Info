import java.util.ArrayList;

public class Club extends java.lang.Object{

    private java.lang.String nomClub;
    private ArrayList<Personne> adherents;
    
    public void Club(){
    }

    public void Club(Club c){
        this.nomClub = c.nomClub;
        this.adherents = c.adherents;
    }

    public void Club(String unNomClub, ArrayList<Personne> desAdherents ){
        this.nomClub = unNomClub;
        this.adherents = desAdherents;
    }

    public ArrayList<Personne> getAdherents(){
        return this.adherents;
    }

    public String getNomClub(){
        return this.nomClub;
    }

    public void setNomClub(String unNomClub){
        this.nomClub = unNomClub;
    }

    public void setAdherents(ArrayList<Personne> desAdherents){
        this.adherents = desAdherents;
    }

    public void init(){
        this.nomClub = "Inconnu";
        this.adherents = new ArrayList<Personne>();
    }

    public String toString(){
        return "Nom du club : " + this.nomClub + " Adherents : " + this.adherents;
    }

}
