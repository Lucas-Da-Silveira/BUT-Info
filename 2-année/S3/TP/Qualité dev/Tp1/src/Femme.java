import java.io.*;

public class Femme extends Humain {

    private int fertilite;

    Femme(String nom) {
        super(nom);
        fertilite = 0;
    }

    Femme(int age, int poids, String nom, int fertilite) {
        super(age, poids, nom);
        this.fertilite = fertilite;
    }

    int getFertilite() {
        return fertilite;
    }

    public void vieillir() {
        super.vieillir();

        if (age == 15){
            fertilite = loto.nextInt(0,101);
        }
        if (age <= 20) poids = 3+(int)(2.6*age);
        else if (age >= 50) poids += (age % 2);
    }

    public Humain rencontre (Homme h) {
        if(!(this.age > 15 && this.age < 50 && h.age > 15)) return null;
        if(!(this.poids > 150 || h.poids > 150 )) return null;

        int f = loto.nextInt(0,101);
        if (!(f > fertilite)) return null;

        int p = loto.nextInt(0,101);
        if (p < 50){
            enfant = new Homme(ho.getnom() + fe.getnom());
        }else{
            enfant = new Femme(ho.getnom() + fe.getnom());
        }

        int g = loto.nextInt(0,21);
        ho.grossir(g);
        fe.grossir(10);

        return enfant;
    }

    protected void setEsperanceVie() {
        // à compléter
    }
}