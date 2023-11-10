package izly;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PurseunitTest {
    @Test
    public void testCredit() throws Exception {
        Purse purse = new Purse();
        double solde = purse.getSolde();
        purse.credit(50.0);
        Assertions.assertEquals(solde+50, purse.getSolde());
    }

    @Test
    public void testDebit() throws Exception {
        Purse purse = new Purse();
        purse.credit(50);
        double solde = purse.getSolde();
        purse.debit(50.0);
        Assertions.assertEquals(solde-50, purse.getSolde());
    }

    @Test
    public void testSoldeJamaisNegatif()  {
        Purse purse = new Purse();
        double solde = purse.getSolde();
        Assertions.assertThrows(SoldeNegatifInterditException.class, () -> {
            purse.debit(solde +1);
        });
    }

    @Test
    public void testSoldeToujoursInferieurAuPlafondFixeALaCreation(){
        double plafond =100;
        Purse purse = new Purse(plafond);
        Assertions.assertThrows(PlafondDepasseInterditException.class, () -> {
            purse.credit(plafond +1);
        });
    }

    @Test
    public void testSoldeNegatifQuandMeme(){
        Purse purse = new Purse(100);
        Assertions.assertThrows(MontantNegatifInterditException.class, () -> {
            purse.credit(-150);
        });
        Assertions.assertThrows(MontantNegatifInterditException.class, () -> {
            purse.debit(-150);
        });
    }
}
