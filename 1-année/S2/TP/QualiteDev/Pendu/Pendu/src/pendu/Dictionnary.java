package pendu;

import java.io.*;
import java.util.*;

public class Dictionnary {

    List<String> lesMots;
    /* Avec ce constructeur, la classe est difficilement testable */
    /*public Dictionnary() {
        File file = new File("resources/francais-divers1.txt");
        Scanner reader = null;
        try {
            reader = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (reader.hasNext())
            lesMots.add(reader.nextLine());
        reader.close();

    }*/

    /* un refactoring du construteur et de son point d'appel permet de se passer du fichier pour les tests.
    * Le type File (correspondant à un support physique est remplacé par un Reader qui peut être instancié soit par
    * un FileReader pour lecture de fichier, soit à un StringReader pour utiliser des chaines de caractères dans
    * les tests*/
    public Dictionnary(Reader file) {
        lesMots = new ArrayList<String>();
        Scanner reader = new Scanner(file);
        while (reader.hasNext())
            lesMots.add(reader.nextLine());
        reader.close();

    }


    public String unMotAuHazard(Random rand) {
        return lesMots.get(rand.nextInt(lesMots.size()));
    }
}
