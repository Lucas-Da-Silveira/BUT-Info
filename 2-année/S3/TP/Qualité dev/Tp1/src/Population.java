import java.util.*;
import java.io.*;

class Population {

    List<Humain> pop;

    Population() {
        pop = new ArrayList<Humain>();
    }

    public void vider() {
        this.pop.clear();
    }

    public void addHumain(Humain h) {
        this.pop.add(h);
    }

    public Humain getHumain(int index) {
        this.pop.get(index);
    }

    public Humain removeHumain(Humain h) {
        this.pop.remove(h);
    }

    public Humain removeHumain(int index) {
        this.pop.remove(index);
    }

    public int taille() {
        return this.pop.size();
    }

    public void vieillir() {
        for (int i=0; i < this.taille(); i++){
            this.pop.get(i).vieillir();

        }
    }

    public void print() {
        for (Humain h : this.pop) {
            System.out.println(h);
        }
    }
}