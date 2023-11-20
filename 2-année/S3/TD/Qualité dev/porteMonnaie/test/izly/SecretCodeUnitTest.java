package izly;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Random;

public class SecretCodeUnitTest {
    
    private CodeSecret codeSecret;
    private Random randomGenerator;

    @BeforeEach
    public void setup(){
        randomGenerator = Mockito.mock(Random.class);
        Mockito.when(randomGenerator.nextInt(10)).thenReturn(2,5,7,1);
        codeSecret = CodeSecret.createCode(randomGenerator);
    }

    @Test
    public void TestRevelerCode(){
        CodeSecret codeSecret = CodeSecret.createCode(new Random());
        Assertions.assertTrue(isCode(codeSecret.revelerCode()));
        Assertions.assertEquals("xxxx", codeSecret.revelerCode());
    }

    private boolean isCode(String code) {
        if(code.length() != 4) return false;
        try {
            Integer.parseInt(code);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    @Test
    public void testCodeSecretGenererAleatoirement(){
        Assertions.assertEquals("2571", codeSecret.revelerCode());
        Mockito.verify(randomGenerator, Mockito.times(4)).nextInt(10);
    }

    @Test
    public void testVerifierCode() throws CodeBloqueException {
        Assertions.assertTrue(codeSecret.verifierCode("2571"));
        Assertions.assertFalse(codeSecret.verifierCode("5271"));
    }

    @Test
    public void testCodeBloqueApres3EssaisFauxConsecutifs() throws CodeBloqueException {
        Assertions.assertFalse(codeSecret.verifierCode("5271"));
        Assertions.assertFalse(codeSecret.verifierCode("5271"));
        Assertions.assertFalse(codeSecret.verifierCode("5271"));
        Assertions.assertThrows(CodeBloqueException.class, () -> {
            codeSecret.verifierCode("5271");
        });
    }

    @Test
    public void testVerifierCodeSansMock() throws CodeBloqueException {
        CodeSecret codeSecret = CodeSecret.createCode(new Random());
        String boncode = codeSecret.revelerCode();
        Assertions.assertTrue(codeSecret.verifierCode(boncode));
        Assertions.assertFalse(codeSecret.verifierCode(alterCode(boncode)));
    }

    private String alterCode(String boncode) {
        if(boncode.charAt(0)=='1'){
            return boncode.replace('1','0');
        }
        else{
            return boncode.replace(boncode.charAt(0), '1');
        }
    }
    
}
