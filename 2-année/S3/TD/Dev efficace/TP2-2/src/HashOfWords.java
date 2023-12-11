import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class HashOfWords {
    private HashMap<Integer, String> mots;
        private Map<Integer, String> hashTable;

    public HashOfWords(int tableType) {
        switch (tableType) {
            case 0:
                this.hashTable = new HashMap<>();
                break;
            case 1:
                this.hashTable = new TreeMap<>();
                break;
            case 2:
                this.hashTable = new LinkedHashMap<>();
                break;
            default:
                throw new IllegalArgumentException("Invalid table type");
        }
        loadWordsFromFile();
    }

    private void loadWordsFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("./src/mots.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                int hash = line.hashCode();
                hashTable.put(hash, line);
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture du fichier mots.txt");
        }
    }

    public ArrayList<String> findValuesList(ArrayList<String> words) {
        ArrayList<String> foundWords = new ArrayList<>();
        for (String word : words) {
            if (hashTable.containsValue(word)) {
                foundWords.add(word);
            }
        }
        return foundWords;
    }

    public ArrayList<String> findValuesToSet(ArrayList<String> words) {
        HashSet<String> valuesSet = new HashSet<>(mots.values());
        ArrayList<String> foundWords = new ArrayList<>();

        for (String word : words) {
            if (valuesSet.contains(word)) {
                foundWords.add(word);
            }
        }
        return foundWords;
    }

    public ArrayList<String> findKeys(ArrayList<String> words) {
        ArrayList<String> foundWords = new ArrayList<>();

        for (String word : words) {
            int hash = word.hashCode();
            if (mots.containsKey(hash)) {
                foundWords.add(word);
            }
        }
        return foundWords;
    }
}
