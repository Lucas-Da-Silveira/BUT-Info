public class Main {
    public static void main(String[] args){

        Population population = new Population();

        int nbTours = Integer.parseInt(args[0]);
        int taillePop = Integer.parseInt(args[1]);

        for(int i = 0; i < taillePop/2; i++){
            population.addHumain(new Homme(20, 70, "H" +i , Humain.loto.nextInt(70, 100)));
            population.addHumain(new Femme(20, 55, "F"+i, Humain.loto.nextInt(1, 100)));
        }

        for(int j =0; j < nbTours; j++){
            int n = Humain.loto.nextInt(taillePop/2);

            for(int i=0; i< n; i++){
                Humain H1 = population.getHumain(i);
                Humain H2 = population.getHumain(i+1);

                Humain enfant;

                if(H1 instanceof Femme){
                    enfant = ((Femme) H1).rencontre((Homme) H2);
                }else{
                    enfant = ((Homme) H1).rencontre((Femme) H2);
                }

                if (enfant != null){
                    population.addHumain(enfant);
                }
            }
        }

        population.vieillir();
        population.print();
    }
}
