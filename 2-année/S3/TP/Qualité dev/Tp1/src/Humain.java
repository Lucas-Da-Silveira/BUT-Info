import java.io.*;
import java.util.*;

public class Humain {

    protected static Random loto = new Random(Calendar.getInstance().getTimeInMillis());
    protected int age;
    protected int poids;
    protected String nom;
    protected int esperanceVie;

    Humain(String nom) {
        this.nom = nom;
        this.age = 0;
        this.poids = 3;
        this.esperanceVie = 70;

    }

    Humain(int age, int poids, String nom) {
        this.nom = nom;
        this.age = age;
        this.poids = poids;
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
        age++;
    }

    public void grossir(int p) {
        poids += p;
    }

    public boolean isDead() {
        return age > esperanceVie;
    }

    public void print() {
        System.out.println("Nom : " + nom + " Age : " + age + " Poids : " + poids);
    }
}