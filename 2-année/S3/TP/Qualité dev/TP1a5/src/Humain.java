import java.io.*;
import java.util.*;

public class Humain implements Comparable<Humain>{

    protected static Random loto = new Random(Calendar.getInstance().getTimeInMillis());
    protected int age;
    protected int poids;
    protected String nom;
    protected int esperanceVie;
    private int salaire;

    Humain(String nom) {
        this.nom = nom;
        this.age = 0;
        this.poids = 3;
        this.setEsperanceVie();
    }

    Humain(int age, int poids, String nom) {
        this.age = age;
        this.poids = poids;
        this.nom = nom;
        this.setEsperanceVie();
    }
    public Humain rencontre(Humain h2) {
        Humain enfant = null;

        if(this.isHomme() && h2.isFemme()) {
            Homme ho = (Homme)this;
            Femme fe = (Femme)h2;

            int b = loto.nextInt(0, 101);
            if(b > ho.getBatifolage()) return null;

            if(fe.age > 15 && fe.age < 50 && ho.age > 15) {
                if(ho.poids > 150 || fe.poids > 150) return null;

                int c = loto.nextInt(0, 101);
                if(c > fe.getFertilite()) return null;

                int p = loto.nextInt(0, 101);
                if(p < 50) {
                    enfant = new Homme(ho.getNom() + fe.getNom());
                } else {
                    enfant = new Femme(ho.getNom() + fe.getNom());
                }

                int g = loto.nextInt(-10, 11);
                this.grossir(g);
                fe.grossir(10);

            }
        } else if(this.isFemme() && h2.isHomme()) {
            Femme fe = (Femme)this;
            Homme ho = (Homme)h2;

            if(fe.age > 15 && fe.age < 50 && ho.getAge() > 15) {
                if(fe.poids > 150 || ho.getPoids() > 150) return null;

                int f = loto.nextInt(0, 101);
                if(f > fe.getFertilite()) return null;

                int p = loto.nextInt(0, 101);
                if(p < 50) {
                    enfant = new Homme(ho.getNom() + fe.getNom());
                } else {
                    enfant = new Femme(ho.getNom() + fe.getNom());
                }

                int g = loto.nextInt(0, 21);
                ho.grossir(g);
                fe.grossir(10);
            }
        }
        return enfant;
    }

    @Override
    public int compareTo(Humain h) {
        return Integer.compare(this.age, h.age);
    }

    int getBatifolage() {
        return 0;
    }

    boolean isHomme(){
        return this instanceof Homme;
    }

    boolean isFemme(){
        return this instanceof Femme;
    }

    void setNom(String nom) {
        this.nom = nom;
    }

    void setAge(int age) {
        this.age = age;
    }

    void setPoids(int poids) {
        this.poids = poids;
    }

    int getAge() {
        return this.age;
    }

    int getPoids() {
        return this.poids;
    }

    String getNom() {
        return this.nom;
    }

    protected void setEsperanceVie() {
        this.esperanceVie = 70;
    }

    public void vieillir() {
        age ++;
    }

    public void grossir(int p) {
        poids += p;
    }

    public boolean isDead() {
        return age > esperanceVie;
    }

    public void print() {
        System.out.println("Nom : " + nom + " Age : " + age + " Poids : " + poids + " Espérence de vie : " + this.esperanceVie + " Salaire : " + this.salaire);
    }
}