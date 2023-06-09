package pendu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Mot {
    private final String motCache;
    private final List<Character> lettresDecouvertes;
    private final List<Character> lettresDuMot;

    private Mot(String motCache, List<Character> lettresDuMot) {
        this.motCache = motCache;
        this.lettresDuMot = lettresDuMot;
        lettresDecouvertes = new ArrayList<>();
    }

    public static Mot createMot(Dictionnary dico) {
        String motCache = dico.unMotAuHazard();
        List<Character> lettresDuMot = new ArrayList<Character>();
        for (int i = 0; i < motCache.length(); i++) {
            if (!lettresDuMot.contains(motCache.charAt(i))) {
                lettresDuMot.add(motCache.charAt(i));
            }
        }
        return new Mot(motCache,lettresDuMot);
    }

    public void affiche() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < motCache.length(); i++) {
              if (lettresDecouvertes.contains(motCache.charAt(i)) )
                 builder.append(motCache.charAt(i));
            else
                  builder.append('-');
        }
        System.out.println(builder);
    }

    public boolean contient(char lettre) {
        return motCache.indexOf(lettre) != -1;
    }

    public void decouvre(char lettre) {
        lettresDecouvertes.add(lettre);
    }

    public boolean estTrouve() {
        return new HashSet<>(lettresDecouvertes).containsAll(lettresDuMot);
    }

    public String getMotCache() {
        return motCache;
    }

    public boolean estCache() {
        return lettresDecouvertes.isEmpty();
    }
}
