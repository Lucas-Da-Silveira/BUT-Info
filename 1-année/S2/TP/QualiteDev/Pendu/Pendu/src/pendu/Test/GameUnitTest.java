package pendu.Test;
import pendu.Game;
import pendu.Joueur;
import pendu.Mot;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;



public class GameUnitTest {

    @Test
    public void testPlay(){
        Joueur joueur = Mockito.mock(Joueur.class);
        Mot mot = Mockito.mock(Mot.class) ;
        Game game = new Game(joueur, mot);

        Mockito.when(mot.estTrouve()).thenReturn(true);
        Mockito.when(joueur.estMort()).thenReturn(true);

        game.play();

        Mockito.verify(mot).getMotCache();
    }

}
