import java.util.*;

class Population {
    List<Humain> pop;
    public int deadCount;
    Population() {
        this.pop = new ArrayList<Humain>();
        this.deadCount = 0;
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
        for(int i = 0; i < this.taille(); i++) {
            this.getHumain(i).vieillir();
            if(this.getHumain(i).getAge() == 18 && this.getHumain(i).isGarcon()){
                Homme h = (Homme)this.getHumain(i);
                this.addHumain(new Homme(h.getAge(), h.getPoids(), h.getNom(), h.getBatifolage(), 0));
                this.removeHumain(i);
            }else if(this.getHumain(i).getAge() == 18 && this.getHumain(i).isFille()){
                Femme f = (Femme)this.getHumain(i);
                this.addHumain(new Femme(f.getAge(), f.getPoids(), f.getNom(),f.getFertilite()));
                this.removeHumain(i);
            }
            if(this.getHumain(i).isDead()) {
                this.deadCount++;
                this.removeHumain(i);
            }
        }
    }

    public int getDeadCount() {
        return this.deadCount;
    }

    public void sort() {
        Collections.sort(this.pop);
    }
    public void print() {
        for(Humain h : this.pop) {
            h.print();
        }
    }
}