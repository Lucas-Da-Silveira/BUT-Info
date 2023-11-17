package izly;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


public class PurseunitTest {
    @BeforeEach
    public void setup()  {
        codeFaux = "1234";
        codeJuste ="5678";
        pinCode = Mockito.mock(CodeSecret.class);
        Mockito.when(pinCode.verifierCode(codeJuste)).thenReturn(true);
        Mockito.when(pinCode.verifierCode(codeFaux)).thenReturn(false);
        purse = new Purse(100, pinCode, 100);
    }
    String codeFaux ;
    String codeJuste;
    CodeSecret pinCode;
    Purse purse;
    @Test
    public void testCredit() throws Exception {
        double solde = purse.getSolde();
        purse.credit(50.0);
        Assertions.assertEquals(solde+50, purse.getSolde());
    }

    @Test
    public void testDebit() throws Exception {
        purse.credit(50);
        double solde = purse.getSolde();
        purse.debit(50.0, codeJuste);
        Assertions.assertEquals(solde-50, purse.getSolde());
    }

    @Test
    public void testSoldeJamaisNegatif()  {
        double solde = purse.getSolde();
        Assertions.assertThrows(SoldeNegatifInterditException.class, () -> {
            purse.debit(solde +1, codeJuste);
        });
    }

    @Test
    public void testSoldeToujoursInferieurAuPlafondFixeALaCreation(){
        Assertions.assertThrows(PlafondDepasseInterditException.class, () -> {
            purse.credit(100 +1);
        });
    }

    @Test
    public void testSoldeNegatifQuandMeme(){
        Assertions.assertThrows(MontantNegatifInterditException.class, () -> {
            purse.credit(-150);
        });
        Assertions.assertThrows(MontantNegatifInterditException.class, () -> {
            purse.debit(-150, codeJuste);
        });
    }

    @Test
    public void testDebitRejeterSurCodeFaux(){
        Mockito.when(pinCode.verifierCode(codeFaux)).thenReturn(false);
        Assertions.assertThrows(CodeSecretErroneException.class, () -> {
            purse.debit(20, codeFaux);
        });
    }

    @Test
    public void testOperationDeCreditEtDebitRejeteeSurCodeBloque(){
        Mockito.when(pinCode.isBlocked()).thenReturn(true);
        Assertions.assertThrows(purseBloqueException.class, () -> {
            purse.credit(50);
        });
        Assertions.assertThrows(purseBloqueException.class, () -> {
            purse.debit(50, codeJuste);
        });
    }

    @Test
    public void testDureeDeVieLimiteAnOperations() throws Exception{
        Purse purse = new Purse(100, pinCode, 3);
        purse.credit(50);
        purse.debit(20, codeJuste);
        purse.debit(25, codeJuste);
        Assertions.assertThrows(NoOpMAxAtteindException.class, ()-> {
            purse.credit(60);
        });
    }
}
