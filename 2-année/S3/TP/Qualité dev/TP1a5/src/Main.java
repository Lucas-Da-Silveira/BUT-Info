import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Population population = new Population();

        int nbTours = Integer.parseInt(args[0]);
        int taillePop = Integer.parseInt(args[1]);

        for (int i = 0; i < taillePop / 2; i++) {
            population.addHumain(new Homme(20, 70, "H" + i, Humain.loto.nextInt(70, 101)));
            population.addHumain(new Femme(20, 55, "F" + i, Humain.loto.nextInt(1, 101)));
        }

        for (int j = 0; j < nbTours; j++) {
            int n = Humain.loto.nextInt(0,taillePop / 2+1);

            for (int i = 0; i < n; i++) {
                int i1 = Humain.loto.nextInt(0,population.taille());
                int i2 = Humain.loto.nextInt(0,population.taille());

                Humain h1 = population.getHumain(i1);
                Humain h2 = population.getHumain(i2);
                Homme ho;
                Femme fe;

                if (h1.isHomme() && h2.isFemme()) {
                    ho = (Homme) h1;
                    fe = (Femme) h2;

                    Humain bebe = ho.rencontre(fe);
                    if (bebe != null) {
                        population.pop.add(bebe);
                        System.out.println("Un enfant est né !");
                    }
                } else if (h1.isFemme() && h2.isHomme()) {
                    fe = (Femme) h1;
                    ho = (Homme) h2;

                    Humain bebe = fe.rencontre(ho);
                    if (bebe != null) {
                        population.pop.add(bebe);
                        System.out.println("Un enfant est né !");
                    }
                }

                population.vieillir();
                population.print();
            }
        }
    }
}