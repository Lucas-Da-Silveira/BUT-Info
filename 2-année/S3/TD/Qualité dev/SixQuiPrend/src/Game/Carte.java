package Game;

import java.util.Objects;

public class Carte {
    private final int numero;
    private final int tetesDeTaureau;

    public Carte(int numero, int tetesDeTaureau) {
        this.numero = numero;
        this.tetesDeTaureau = tetesDeTaureau;
    }

    public int getNumero() {
        return numero;
    }

    public int getTetesDeTaureau() {
        return tetesDeTaureau;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Carte carte = (Carte) obj;
        return numero == carte.numero && tetesDeTaureau == carte.tetesDeTaureau;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, tetesDeTaureau);
    }
}
