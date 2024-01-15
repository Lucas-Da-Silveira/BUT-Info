public class Batiment {

    private Facade devanture;
    private Toiture toit;
    private int prix;

    Batiment(Facade devanture, Toiture toit, int prix){
        this.devanture=devanture;
        this.toit=toit;
        this.prix=prix;
    }

    Batiment(){}

    public int getPrix(){
        return(this.prix);
    }

    public Toiture getToit(){
        return(this.toit);
    }

    public Facade getDevanture(){
        return(this.devanture);
    }

    public void setToit(Toiture t){
        this.toit=t;
        this.prix=this.prix+750;
    }

    public void setDevanture(Facade f){
        this.devanture=f;
        this.prix=this.prix+750;
    }

    public void peindre_toit(String color){
        this.getToit().tp.peindre(color);
        this.prix+=250;
    }

    public void peindre_facade(String color){
        this.getDevanture().fp.peindre(color);
        this.prix+=400;
    }

}
