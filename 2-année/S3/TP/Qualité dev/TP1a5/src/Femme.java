import java.io.*;

public class Femme extends Humain {

    private int fertilite;
    Femme(String nom) {
        super(nom);
        this.fertilite = 0;
    }
    
    Femme(int age, int poids, String nom, int fertilite) {
        super(age, poids,nom);
        this.fertilite = fertilite;
        this.setEsperanceVie();
    }

    int getFertilite() {
        return this.fertilite;
    }

    public void vieillir() {
        super.vieillir();
        if(this.getAge() == 15){
            this.fertilite = loto.nextInt(0, 101);
        }

        if (age <= 20) poids = 3+(int)(2.6*age);
        else if (age >= 50) poids += (age % 2);
    }

    protected void setEsperanceVie() {
        this.esperanceVie = loto.nextInt(55, 96);
    }
}