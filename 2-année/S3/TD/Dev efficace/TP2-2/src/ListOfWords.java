import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListOfWords {
    private List<String> mots;

    public ListOfWords() {
        this.mots = new ArrayList<>();
        loadWordsFromFile();
    }

    private void loadWordsFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("./src/mots.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                mots.add(line);
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture du fichier mots.txt");
        }
    }

    public ArrayList<String> randomSelect(int nbElements) {
        if (nbElements > mots.size()) {
            throw new IllegalArgumentException("Nombre d'éléménent > nombre de mots dans la liste");
        }

        ArrayList<String> selectedWords = new ArrayList<>(mots);
        Collections.shuffle(selectedWords);
        return new ArrayList<>(selectedWords.subList(0, nbElements));
    }
    
    public ArrayList<String> find(List<String> inputList) {
        ArrayList<String> result = new ArrayList<>();

        for (String word : inputList) {
            if (mots.contains(word)) {
                result.add(word + " yes");
            } else {
                result.add(word + " no");
            }
        }

        return result;
    }
}
