import java.io.*;

public class Homme extends Humain {

    private int batifolage;

    Homme(String nom) {
        super(nom);
        batifolage = 0;
    }

    Homme(int age, int poids, String nom, int batifolage) {
        super(age, poids, nom);
        this.batifolage = batifolage;
    }

    public Humain rencontre (Femme f) {
        int b = loto.nextInt(0,101);
        if(!(b < batifolage)) return null;

        if(!(f.age > 15 && f.age < 50 && this.age > 15)) return null;
        if(!(this.poids > 150 || f.poids > 150)) return null;

        int c = loto.nextInt(0,101);
        if(!(c > f.fertilite)) return null;

        int p = loto.nextInt(0,101);
        if (p < 50){
            enfant = new Homme(this.nom + f.nom);
        }else{
            enfant = new Femme(this.nom + f.nom);
        }

        int g = loto.nextInt(-10,10);
        this.grossir(g);
        fe.grossir(10);
    }

    public void vieillir() {
        super.vieillir();

        if(age > 15){
            batifolage = loto.nextInt(70,101);
        }
        if (age > 30) {
            batifolage = loto.nextInt(20,51);
        }
        if (age > 60){
            batifolage = loto.nextInt(50, 101);
        }

        if (age <= 20) poids = 3+(int)(3.6*age);
        else if (age >= 50) poids += (age % 2);
    }

    protected void setEsperanceVie() {
        this.esperanceVie = loto.nextInt(50, 81);
    }
}