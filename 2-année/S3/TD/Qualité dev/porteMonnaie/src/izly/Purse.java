package izly;

public class Purse {
    double solde;
    public double getSolde() {
        return solde;
    }

    public void credit(double montant) {
        solde += montant;
    }

    public void debit(double montant) throws SoldeNegatifInterditException {
        if(montant >solde) throw new SoldeNegatifInterditException();
        solde -= montant;
    }
}
