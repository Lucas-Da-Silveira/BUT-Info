public class Carte {

    public Valeur32 v32;
    public Valeur52 v52;
    public Bois c;

    public Carte(Valeur32 v, Bois c){
        this.v32 = v;
        this.c =c;
    }

    public Carte(Valeur52 v, Bois c){
        this.v52 = v;
        this.c =c;
    }

    @Override
    public String toString() {
        return "c = "+c+", v32 = "+v32+", v52 = "+v52;
    }
}
