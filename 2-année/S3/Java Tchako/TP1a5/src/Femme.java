import java.io.*;
public class Femme extends Humain {
    private int fertilite;
    Femme(String nom) {
        super(nom);
        this.fertilite = 0;
        this.setEsperanceVie();
    }
    Femme(int age, int poids, String nom, int fertilite) {
        super(age, poids, nom);
        this.fertilite = fertilite;
        this.setEsperanceVie();
    }
    int getFertilite() {
        return this.fertilite;
    }

    void setFertilite(int fertilite) {
        this.fertilite = fertilite;
    }
    @Override
    public void vieillir(Aging a) {
        super.vieillir(a);
        if(this.getAge() == 15) {
            this.fertilite = loto.nextInt(0, 101);
        }
        if (age <= 20) a.gainWeight(this);
        else if (age >= 50) a.gainWeight(this);
    }

/*    @Override
    public int compareTo(Humain h) {
        if(this.age < h.age) {
            return -1;
        } else if (this.age == h.age) {
            if(h.isHomme()) {
                return -1;
            } else {
                Femme fe = (Femme)h;
                return this.fertilite - fe.fertilite;
            }
        } else {
            return 1;
        }
    }*/

    @Override
    public Humain rencontre(Humain h2,Meetic m) {
        Humain enfant = null;
        try{
            Main.meetCount++;
            if(h2.isFemme()) throw new BreedingForbiddenException(this, h2);
            if(h2.isHomme()) {
                Homme ho = (Homme) h2;

                if (this.getAge() < 50) {
                    enfant = genEnfant(this, ho, m);
                }
            } else if(h2.isGarcon() && h2.getAge() >= 15){
                Garcon gr = (Garcon) h2;
                enfant = genEnfant(this, gr, m);
                Main.pedoCount++;
            }
        }catch(BreedingForbiddenException e){
            System.out.println(e.getMessage());
        }
        return enfant;
    }

    protected Humain genEnfant(Humain fe, Humain ho, Meetic m) {
        Homme hoh = (Homme) ho;
        Femme fef = (Femme) fe;
        Humain enfant = null;

        try {
            if (fef.getAge() < 50) {
                if (fef.getPoids() > 150 || hoh.getPoids() > 150) return null;

                int f = loto.nextInt(0, 101);
                if (f > fef.getFertilite()) return null;

                int p = loto.nextInt(0, 101);
                if (p < 50) {
                    enfant = new Garcon(Humain.genPrenom());
                } else {
                    enfant = new Fille(Humain.genPrenom());
                }

                //int g = loto.nextInt(0, 21);
                //hoh.grossir(g);
                m.gainWeight(hoh, fef);
                //fef.grossir(10);
            }else{
                throw new BreedingForbiddenException(this, ho);
            }
        }catch (BreedingForbiddenException e){
            System.out.println(e.getMessage());
        }
        return enfant;
    }
    @Override
    protected void setEsperanceVie() {
        this.esperanceVie = loto.nextInt(55, 96);
    }

}