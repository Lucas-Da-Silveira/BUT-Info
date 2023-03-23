package pendu;

import java.util.ArrayList;
import java.util.List;

public class Mot {
    private String motCache;
    private List<Character> lettresDecouvertes;
    private List<Character> lettresDuMot;

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
        for (int i = 0; i < motCache.length(); i++) {
              if (lettresDecouvertes.contains(motCache.charAt(i)) )
                  System.out.print(motCache.charAt(i));
            else
                  System.out.print("-");            
        }
        System.out.println("");
        
    }

    public boolean contient(char lettre) {
        return motCache.indexOf(lettre) != -1;
    }

    public void decouvre(char lettre) {
        lettresDecouvertes.add(lettre);
    }

    public boolean estTrouve() {
        return lettresDecouvertes.containsAll(lettresDuMot);
    }

    public String getMotCache() {
        return motCache;
    }

    public boolean estCache() {
        return lettresDecouvertes.isEmpty();
    }
}
