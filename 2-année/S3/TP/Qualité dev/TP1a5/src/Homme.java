import java.io.*;
public class Homme extends Humain {
    private int batifolage;
    Homme(String nom) {
        super(nom);
    }
    Homme(int age, int poids, String nom, int batifolage) {
        super(age, poids,nom);
        this.batifolage = batifolage;
        this.setEsperanceVie();
    }
    public Humain rencontre (Femme f) {
        Humain enfant;
        int b = loto.nextInt(0, 101);
        if (b < this.batifolage) return null;

        if (!(f.age > 15 && f.age < 50 && this.age > 15)) return null;
        if (this.poids > 150 || f.poids > 150) return null;

        int c = loto.nextInt(0, 101);
        if(c > f.getFertilite()) return null;

        int p = loto.nextInt(0, 101);
        if(p < 50){
            enfant = new Homme(f.nom + this.nom);
        }else{
            enfant = new Femme(f.nom + this.nom);
        }
        int g = loto.nextInt(-10, 10);
        f.grossir(g);
        this.grossir(10);

        return enfant;
    }
    public void vieillir() {
        super.vieillir();

        if (age > 15) this.batifolage = loto.nextInt(70, 100);
        if (age > 30) this.batifolage = loto.nextInt(20, 50);
        if(age > 60) this.batifolage = loto.nextInt(50, 100);

        if (age <= 20) poids = 3+(int)(3.6*age);
        else if (age >= 50) poids += (age % 2);
    }
    protected void setEsperanceVie() {
        this.esperanceVie = loto.nextInt(50, 80);
    }
}