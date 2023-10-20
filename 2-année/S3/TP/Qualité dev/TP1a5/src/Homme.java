import java.io.*;
import java.util.Collections;

public class Homme extends Humain {
    protected int batifolage;
    private int salaire;
    Homme(String nom) {
        super(nom);
        this.batifolage = 0;
        this.salaire = 0;
        this.setEsperanceVie();
    }
    Homme(int age, int poids, String nom, int batifolage, int salaire) {
        super(age, poids, nom);
        this.batifolage = batifolage;
        this.salaire = salaire;
        this.setEsperanceVie();
    }
    public int getBatifolage() {
        return this.batifolage;
    }

    public int getSalaire() {
        return this.salaire;
    }
    @Override
    public void vieillir(Aging a) {
        if(this.age == 18) {
            this.salaire = loto.nextInt(1000, 11001);
        }
        super.vieillir(a);

        if(age > 15) this.batifolage = loto.nextInt(70, 101);
        if(age > 30) this.batifolage = loto.nextInt(20, 51);
        if(age > 60) this.batifolage = loto.nextInt(50, 101);

        if (age <= 20) a.gainWeight(this);
        else if (age >= 50) a.gainWeight(this);
    }
    @Override
    protected void setEsperanceVie() {
        this.esperanceVie = loto.nextInt(50, 81);
    }

/*    @Override
    public int compareTo(Humain h) {
        if(this.age < h.age) {
            return -1;
        } else if (this.age == h.age) {
            if(h.isFemme()) {
                return -1;
            } else {
                Homme ho = (Homme)h;
                return this.salaire - ho.salaire;
            }
        } else {
            return 1;
        }
    }*/

    @Override
    public Humain rencontre(Humain h2, Meetic m){
        Humain enfant = null;
        try{
            Main.meetCount++;
            if (h2.isHomme()) throw new BreedingForbiddenException(this, h2);
            if(h2.isFemme()) {
                Femme fe = (Femme) h2;

                if (this.getAge() < 50){
                    enfant = genEnfant(fe, this, m);
                }
                else{
                    throw new BreedingForbiddenException(this, h2);
                }
            } if(h2.isFille() && h2.getAge() >= 15) {
                Fille fi = (Fille) h2;
                enfant = genEnfant(fi, this, m);

            }else{
                Main.pedoCount++;
                throw new BreedingForbiddenException(this, h2);
            }
        }catch (BreedingForbiddenException e){
            System.out.println(e.getMessage());
        }
        return enfant;
    }

    protected Humain genEnfant(Humain fe, Humain ho, Meetic m) {
        Homme hoh = (Homme) ho;
        Femme fef = (Femme) fe;

        Humain enfant = null;
        try{

            if(hoh.isGarcon() && fef.isFille()) return null;
            if (hoh.getBatifolage() >= 0) {
                int b = loto.nextInt(0, 101);
                if (b > hoh.getBatifolage()) return null;

                if (fef.getAge() < 50) {
                    if (hoh.poids > 150 || fef.poids > 150) return null;

                    int c = loto.nextInt(0, 101);
                    if (c > fef.getFertilite()) return null;

                    int p = loto.nextInt(0, 101);
                    if (p < 50) {
                        enfant = new Garcon(Humain.genPrenom());
                    } else {
                        enfant = new Fille(Humain.genPrenom());
                    }

                    //int g = loto.nextInt(-10, 11);
                    //hoh.grossir(g);
                    m.gainWeight(hoh, fef);
                    //fef.grossir(10);
                } else {
                    throw new BreedingForbiddenException(this, ho);
                }
            }else{
                throw new NoBreedingException(this, ho);
            }
        }catch (BreedingForbiddenException | NoBreedingException e){
            System.out.println(e.getMessage());
        }
        return enfant;
    }

    @Override
    public void print() {
        super.print();
        System.out.println("\u001B[34m | Salaire: " + this.salaire);
    }
}