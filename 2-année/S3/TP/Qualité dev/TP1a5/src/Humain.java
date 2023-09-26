import java.io.*;
import java.util.*;
public class Humain implements Comparable<Humain>{
    protected static Random loto = new Random(Calendar.getInstance().getTimeInMillis());
    protected int age;
    protected int poids;
    protected String nom;
    protected int esperanceVie;

    Humain() {
    }
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
        this.age++;
    }
    public void grossir(int p) {
        this.poids += p;
    }
    public boolean isDead() {
        return this.age > this.esperanceVie;
    }
    public boolean isHomme() {
        return this instanceof Homme;
    }
    public boolean isFemme() {
        return this instanceof Femme;
    }
    public boolean isGarcon() {
        return this instanceof Garcon;}
    public boolean isFille() {
        return this instanceof Fille;
    }
    public static String genPrenom() {
        String prenom = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader("/home/lucas/BUT-Info/2-année/S3/TP/Qualité dev/TP1a5/src/prenoms.txt"));
            String line = reader.readLine();
            List<String> prenoms = new ArrayList<>();
            while(line != null) {
                prenoms.add(line);
                line = reader.readLine();
            }
            prenom = prenoms.get(Humain.loto.nextInt(0, prenoms.size()));

        } catch(Exception e) {
            System.err.println(e);
        }

        return prenom;
    }

    public static String genMort() {
        String Mort = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader("/home/lucas/BUT-Info/2-année/S3/TP/Qualité dev/TP1a5/src/mort.txt"));
            String line1 = reader.readLine();
            List<String> Morts = new ArrayList<>();
            while(line1 != null) {
                Morts.add(line1);
                line1 = reader.readLine();
            }
            Mort = Morts.get(Humain.loto.nextInt(0, Morts.size()));

        } catch(Exception e) {
            System.err.println(e);
        }

        return Mort;
    }
    public Humain rencontre(Humain h2) {
        return null;
    }
    @Override
    public int compareTo(Humain h) {
        return Integer.compare(this.age, h.age);
    }
    public void print() {
        if(this.isHomme()) {
            System.out.print("\u001B[34m\nNom: " + this.nom + " | Age: " + this.age + " ans | Poids: " + this.poids + "kg | Espérence de vie: " + this.esperanceVie + " ans");
        } else {
            System.out.println("\u001B[35m\nNom: " + this.nom + " | Age: " + this.age + " ans | Poids: " + this.poids + "kg | Espérence de vie: " + this.esperanceVie + " ans");
        }
    }
}