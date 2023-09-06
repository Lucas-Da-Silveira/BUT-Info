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
            int n = Humain.loto.nextInt(taillePop / 2);

            Humain h1;
            Humain h2;
            Homme ho;
            Femme fe;

            for (int i = 0; i < n; i++) {
                h1 = population.getHumain(i);
                h2 = population.getHumain(i + 1);

                if (h1 instanceof Homme && h2 instanceof Femme) {
                    ho = (Homme) h1;
                    fe = (Femme) h2;

                    Humain bebe = ho.rencontre(fe);
                    if (bebe != null) {
                        population.pop.add(bebe);
                        System.out.println("Un enfant est né !");
                    }
                } else if (h1 instanceof Femme && h2 instanceof Homme) {
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