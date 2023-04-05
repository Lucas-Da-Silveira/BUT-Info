package pendu.Test;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import pendu.Dictionnary;
import pendu.Mot;
import org.mockito.Mockito;

import java.io.StringReader;
import java.util.*;


public class MotUnitTest {

    @Test
    public void testCreateMot() {
        Dictionnary dico = new Dictionnary(new StringReader(""));

        @Override
        public String unMotAuHazard(Random rand){
            return "bienvenue";
        }
    }

    Mot mot = Mot.createMot(dico);
    Assertions.assertEquals("Bienvenue", mot.getMotCache());
    mot.decouvre('b');
    mot.decouvre('i');
    mot.decouvre('e');
    mot.decouvre('n');
    mot.decouvre('v');
    Assertions.assertFalse(mot.estCache());
    mot.decouvre('u');
    Assertions.assertTrue(mot.estTrouve());

    @Test
    public void createMotTest(){
        Dictionnary dico = Mockito.mock(Dictionnary.class);
        Mockito.when(dico.unMotAuHazard(Mockito.any(Random.class))).thenReturn("bienvenue");

        Mot mot = Mot.createMot(dico);
        Assertions.assertEquals("Bienvenue", mot.getMotCache());
        mot.decouvre('b');
        mot.decouvre('i');
        mot.decouvre('e');
        mot.decouvre('n');
        mot.decouvre('v');
        Assertions.assertFalse(mot.estCache());
        mot.decouvre('u');
        Assertions.assertTrue(mot.estTrouve());
    }

    @Test
    public void testContient(){
        Dictionnary dico = mock(Dictionnary.class);
        when(dico.unMotAuHazard(any(Random.class))).thenReturn("bienvenue");
        Mot mot = Mot.createMot(dico);
        Assertions.assertTrue(mot.contient("i"));
        Assertions.assertFalse(mot.contient("a");
    }

    @Test
    public void testAffiche(){
        Dictionnary dico = mock(Dictionnary.class);
        when(dico.unMotAuHazard(any(Random.class))).thenReturn("bienvenue");
        Mot mot = Mot.createMot(dico);
        assertEquals("-------", mot.construitMotAffiche().toString();
        mot.decouvre("e");
        assertEquals("--e---e--e", mot.contruitMotAffiche().toString();
    }
}
