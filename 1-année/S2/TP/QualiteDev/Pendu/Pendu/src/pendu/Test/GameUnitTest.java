package pendu.Test;
import pendu.Game;
import pendu.Joueur;
import pendu.Mot;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Scanner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class GameUnitTest {

    @Test
    public void testPlayCheminIntérieurABIJL(){
        Joueur joueur = Mockito.mock(Joueur.class);
        Mot mot = Mockito.mock(Mot.class) ;
        Game game = new Game(joueur, mot);

        when(mot.estTrouve()).thenReturn(true);
        when(joueur.estMort()).thenReturn(true);

        game.play();

        verify(mot).getMotCache();
        verify(joueur, never()).proposeLettre(any(Scanner.class));
        verify(joueur).estMort();
    }

    @Test
    public void testPlayCheminIntérieurABIKL(){
        Joueur joueur = Mockito.mock(Joueur.class);
        Mot mot = Mockito.mock(Mot.class) ;
        Game game = new Game(joueur, mot);

        when(mot.estTrouve()).thenReturn(true);
        //when(joueur.estMort()).thenReturn(false);

        game.play();

        verify(mot,never()).getMotCache();
        verify(joueur, never()).proposeLettre(any(Scanner.class));
        verify(joueur).estMort();
    }

    @Test
    public void testPlayCheminIntérieurABCIJL(){
        Joueur joueur = Mockito.mock(Joueur.class);
        Mot mot = Mockito.mock(Mot.class) ;
        Game game = new Game(joueur, mot);

        when(mot.estTrouve()).thenReturn(false);
        when(joueur.estMort()).thenReturn(true);

        game.play();

        verify(mot).getMotCache();
        verify(joueur, never()).proposeLettre(any(Scanner.class));
        verify(joueur, times(2)).estMort();
    }

}
