package Game;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JoueurTest {

    private Joueur joueur;

    @BeforeEach
    void setUp() {
        joueur = new Joueur();
    }

    @Test
    void testChoisirCarte() {
        Carte carte = mock(Carte.class);
        joueur.ajouterCarteMain(carte);
        assertTrue(joueur.getMain().contains(carte));

        joueur.choisirCarte();
        verify(carte, times(1)).equals(any());
    }

    @Test
    void testJouerCarte() {
        Joueur adversaire = mock(Joueur.class);
        Carte carte = new Carte(10, 2);
        joueur.jouerCarte(adversaire, carte);
        verify(adversaire, times(1)).recevoirCarte(carte);
    }
}
