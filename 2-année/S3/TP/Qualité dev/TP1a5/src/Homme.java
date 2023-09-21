import java.io.*;
public class Homme extends Humain {
    private int batifolage;
    private int salaire;
    Homme(String nom) {
        super(nom);
        this.batifolage = 0;
        this.salaire = 0;
        this.setEsperanceVie();
    }
    Homme(int age, int poids, String nom, int batifolage, int salaire) {
        super(age, poids,nom);
        this.batifolage = batifolage;
        this.salaire = salaire;
        this.setEsperanceVie();
    }
    @Override
    public void vieillir() {
        if(this.age == 18){
            this.salaire = loto.nextInt(1000, 11001);
        }
        super.vieillir();

        if (age > 15) this.batifolage = loto.nextInt(70, 100);
        if (age > 30) this.batifolage = loto.nextInt(20, 50);
        if(age > 60) this.batifolage = loto.nextInt(50, 100);

        if (age <= 20) poids = 3+(int)(3.6*age);
        else if (age >= 50) poids += (age % 2);
    }

    @Override
    public int compareTo(Humain h) {
        if(this.age < h.age) {
            return -1;
        }else if (this.age == h.age) {
            if(h.isFemme()) {
                return 0;
            } else {
                Homme ho = (Homme)h;
                return this.salaire - ho.salaire;
            }
        }
        else {
            return -1;
        }
    }
    protected void setEsperanceVie() {
        this.esperanceVie = loto.nextInt(50, 80);
    }
}