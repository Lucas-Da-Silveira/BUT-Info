import java.util.*;
import java.io.*;
class Population {
    List<Humain> pop;
    Population() {
        this.pop = new ArrayList<Humain>();
    }
    public void vider() {
        this.pop.clear();
    }
    public void addHumain(Humain h) {
        this.pop.add(h);
    }
    public Humain getHumain(int index) {
        return this.pop.get(index);
    }
    public void removeHumain(Humain h) {
        this.pop.remove(h);
    }
    public void removeHumain(int index) {
        this.pop.remove(index);
    }
    public int taille() {
        return this.pop.size();
    }
    public void vieillir() {
        for (Humain h : this.pop){
            h.vieillir();
            if(h.isDead()) this.pop.remove(h);
        }
    }
    public void print() {
        for (Humain h : this.pop){
            System.out.println(h);
        }
    }
}