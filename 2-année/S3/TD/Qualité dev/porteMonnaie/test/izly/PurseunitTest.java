package izly;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PurseunitTest {
    @Test
    public void testCredit(){
        Purse purse = new Purse();
        double solde = purse.getSolde();
        purse.credit(50.0);
        Assertions.assertEquals(solde+50, purse.getSolde());
    }

    @Test
    public void testDebit() throws SoldeNegatifInterditException {
        Purse purse = new Purse();
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
}
