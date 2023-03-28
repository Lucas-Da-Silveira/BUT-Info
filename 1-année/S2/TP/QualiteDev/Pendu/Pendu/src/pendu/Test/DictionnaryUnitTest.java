package pendu.Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pendu.Dictionnary;

import java.io.StringReader;
import java.util.Random;

public class DictionnaryUnitTest {

    /*@pendu.Test
    public void testDictionnaryConstruct() {
        Dictionnary dictionnary = new Dictionnary(new StringReader("rouge\nvert\njaune"));
        Assertions.assertEquals(3, dictionnary.lesMots.size());
        Assertions.assertEquals(dictionnary.lesMots.contains("rouge"));
        Assertions.assertEquals(dictionnary.lesMots.contains("vert"));
        Assertions.assertEquals(dictionnary.lesMots.contains("jaune"));
    }*/
    
    @Test
    public void testUnMotAuHazard(){
        Dictionnary dictionnary = new Dictionnary(new StringReader("rouge\nvert\njaune"));
        Random random = new Random(){
            @Override
            public int nextInt(int bound) {
                return 1;
            }
        };
        String motAuHazard = dictionnary.unMotAuHazard(random);
        Assertions.assertEquals("vert", motAuHazard);
    }
}
