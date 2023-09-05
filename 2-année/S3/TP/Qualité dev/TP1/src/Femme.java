import java.io.*;

public class Femme extends Humain {

    private int fertilite;
    Femme(String nom) {
        super(nom);
    }
    
    Femme(int age, int poids, String nom, int fertilite) {
        super(age, poids,nom);
        this.fertilite = fertilite;
    }

    int getFertilite() {
        return this.fertilite;
    }

    public void vieillir() {
        age ++;
        if(this.getAge() == 15){
            this.fertilite = loto.nextInt(0, 101);
        }

        if (age <= 20) poids = 3+(int)(2.6*age);
        else if (age >= 50) poids += (age % 2);
    }
    public Humain rencontre (Homme h) {
        Humain enfant;

        if(this.age > 15 && this.age < 50 && h.getAge() > 15){
            if(this.poids > 150 || h.getPoids() >150) return null;

            int f = loto.nextInt(0, 101);
            if (f < this.fertilite) return null;

            int p = loto.nextInt(0, 101);
            if (p < 50){
                enfant = new Homme(h.nom + this.nom);
            }else{
                enfant = new Femme(h.nom + this.nom);
            }

            int g = loto.nextInt(0, 21);
            h.grossir(g);
            this.grossir(10);
        }else{
            return null;
        }

        return enfant;
    }

    protected void setEsperanceVie() {
        this.esperanceVie = loto.nextInt(55, 95);
    }
}