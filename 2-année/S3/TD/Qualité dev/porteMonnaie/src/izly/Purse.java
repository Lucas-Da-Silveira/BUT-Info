package izly;

public class Purse {
    double solde;
    double plafond;

    public Purse() {
        this(100);
    }

    public Purse(double plafond) {
        this.plafond = plafond;
    }

    public double getSolde() {
        return solde;
    }

    public void credit(double montant) throws PlafondDepasseInterditException , MontantNegatifInterditException {
        if(montant < 0) throw new MontantNegatifInterditException();
        if(solde + montant > this.plafond ) throw new PlafondDepasseInterditException();
        solde += montant;
    }

    public void debit(double montant) throws SoldeNegatifInterditException, MontantNegatifInterditException {
        if(montant < 0) throw new MontantNegatifInterditException();
        if(montant >solde) throw new SoldeNegatifInterditException();
        solde -= montant;
    }
}
