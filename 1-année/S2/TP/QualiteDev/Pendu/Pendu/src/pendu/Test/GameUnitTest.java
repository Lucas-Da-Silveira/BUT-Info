package pendu.Test;
import org.junit.jupiter.api.BeforeEach;
import pendu.Game;
import pendu.Joueur;
import pendu.Mot;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Scanner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class GameUnitTest {

    private Joueur joueur;
    private Mot mot;
    private Game game;

    @BeforeEach
    public void setUp(){
        joueur = Mockito.mock(Joueur.class);
        mot = Mockito.mock(Mot.class);
        game = new Game(joueur, mot);
    }

    @Test
    public void testPlayCheminLimiteABIJL(){

        when(mot.estTrouve()).thenReturn(true);
        when(joueur.estMort()).thenReturn(true);

        game.play();

        verify(mot).getMotCache();
        verify(joueur, never()).proposeLettre(any(Scanner.class));
        verify(joueur).estMort();
    }

    @Test
    public void testPlayCheminLimiteABIKL(){

        when(mot.estTrouve()).thenReturn(true);
        //when(joueur.estMort()).thenReturn(false);

        game.play();

        verify(mot,never()).getMotCache();
        verify(joueur, never()).proposeLettre(any(Scanner.class));
        verify(joueur).estMort();
    }

    @Test
    public void testPlayCheminLimiteABCIJL(){

        when(mot.estTrouve()).thenReturn(false);
        when(joueur.estMort()).thenReturn(true);

        game.play();

        verify(mot).getMotCache();
        verify(joueur, never()).proposeLettre(any(Scanner.class));
        verify(joueur, times(2)).estMort();
    }

    @Test
    public void testPlayCheminLimiteNonExecutableABCIKL(){

        when(mot.estTrouve()).thenReturn(false);
        when(joueur.estMort()).thenReturn(true, false);

        game.play();

        verify(mot).getMotCache();
        verify(joueur, never()).proposeLettre(any(Scanner.class));
        verify(joueur, times(2)).estMort();
    }

    @Test
    public void testPlayCheminIntérieurNonExecutableABCDEFHBIJL(){

        when(mot.estTrouve()).thenReturn(false, true);
        when(joueur.estMort()).thenReturn(false, true);
        when(mot.contient(anyChar())).thenReturn(true);

        game.play();

        verify(mot).getMotCache();
        verify(joueur).proposeLettre(any(Scanner.class));
        verify(joueur, times(2)).estMort();
    }

    @Test
    public void testPlayJoueurDecouvreVERTen6coups(){
        when(mot.estTrouve()).thenReturn(false, false, false, false, false, false, true);
        when(joueur.estMort()).thenReturn(false);
        when(joueur.proposeLettre(any(Scanner.class))).thenReturn('V','O','E','R','I','T');
        when(mot.contient('V')).thenReturn(true);
        when(mot.contient('E')).thenReturn(true);
        when(mot.contient('R')).thenReturn(true);
        when(mot.contient('T')).thenReturn(true);


        game.play();

        verify(mot,never()).getMotCache();
        verify(joueur, times(6)).proposeLettre(any(Scanner.class));
        verify(joueur,times(2)).perdVie();
        verify(mot, times(4)).decouvre(anyChar());
    }

    @Test
    public void testPlayCheminIntérieurABCDEFHBIKL(){
    }

    @Test
    public void testPlayCheminIntérieurABCDEFHBCIJL(){
        when(mot.estTrouve()).thenReturn(false, true);
        when(joueur.estMort()).thenReturn(false, true);
        when(mot.contient(anyChar())).thenReturn(true);

        game.play();

        verify(mot).getMotCache();
        verify(joueur).proposeLettre(any(Scanner.class));
        verify(joueur, times(2)).estMort();

    }

    @Test
    public void testPlayCheminIntérieurNonExecutableABCDEFHBCIKL(){
    }

    @Test
    public void testPlayCheminIntérieurABCDEGBIJL(){
    }

    @Test
    public void testPlayCheminIntérieurABCDEGBIKL(){
    }

    @Test
    public void testPlayCheminIntérieurABCDEGBCIJL(){
    }

    @Test
    public void testPlayCheminIntérieurNonExecutableABCDEGBCIKL(){
    }
}
