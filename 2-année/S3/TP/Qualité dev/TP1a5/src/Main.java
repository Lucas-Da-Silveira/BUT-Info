import java.util.ArrayList;

public class Main {
    static int pedoCount = 0;
    public static void main(String[] args) {
        Population population = new Population();

        int nbTours = Integer.parseInt(args[0]);
        int tailleInit = Integer.parseInt(args[1]);

        int nbBebes = 0;


        for(int i = 0; i < tailleInit / 2; i++) {
            int salaire = Humain.loto.nextInt(1000, 11001);
            population.addHumain(new Homme(17, 70, Humain.genPrenom(), Humain.loto.nextInt(70, 101), salaire));
            population.addHumain(new Femme(18, 55, Humain.genPrenom(), Humain.loto.nextInt(1, 101)));
        }

        for(int j = 0; j < nbTours; j++) {
            int n = Humain.loto.nextInt(0, tailleInit / 2 + 1);
            System.out.println("\u001B[0m\n################# Tour " + (j+1) + " #################");
            for(int i = 0; i < n; i++) {
                int i1 = Humain.loto.nextInt(0, population.taille());
                int i2 = Humain.loto.nextInt(0, population.taille());

                Humain h1 = population.getHumain(i1);
                Humain h2 = population.getHumain(i2);

                Humain bebe = h1.rencontre(h2);

                if(bebe != null) {
                    population.addHumain(bebe);
                    System.out.println("\u001B[31m" + h1.getNom() + " baise " + h2.getNom() + "! Un nouveau bébé est arrivé: " + bebe.getNom());
                    nbBebes++;
                }

                if(h1.isGarcon() && h2.isFille() || h1.isFille() && h2.isGarcon()){
                    int accident = Humain.loto.nextInt(0, 101);
                    if(accident < 50){
                        population.removeHumain(h1);
                        System.out.println("\u001B[36m" + h1.getNom() + " " + Humain.genMort());
                    }
                }

            }
            population.sort();
            population.print();
            population.vieillir();
            if(population.taille() == 0) {
                System.out.println("\n\u001B[36mTout le monde est dead :(");
                break;
            }
        }
        System.out.println("\n\u001B[32mNombre de naissances pendant la simulation: " + nbBebes);
        System.out.println("\u001B[32mNombre de décès pendant la simulation: " + population.getDeadCount());
        System.out.println("\u001B[32mTaille finale de la population: " + population.taille());
        System.out.println("\u001B[32mNombre de pédophiles: " + pedoCount);
    }
}