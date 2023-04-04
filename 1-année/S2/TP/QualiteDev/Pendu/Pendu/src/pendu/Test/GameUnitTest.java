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
    public void testPlayCheminIntérieurABCDEFHBIJL(){

        when(mot.estTrouve()).thenReturn(true);
        when(joueur.estMort()).thenReturn(false);
        when(mot.contient(anyChar())).thenReturn(true);

        game.play();

        verify(mot).getMotCache();
        verify(joueur).proposeLettre(any(Scanner.class));
        verify(joueur, times(2)).estMort();
    }

    @Test
    public void testPlayCheminIntérieurABCDEFHBIKL(){
    }

    @Test
    public void testPlayCheminIntérieurABCDEFHBCIJL(){
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
