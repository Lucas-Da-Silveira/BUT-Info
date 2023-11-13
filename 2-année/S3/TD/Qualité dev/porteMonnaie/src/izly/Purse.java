package izly;

public class Purse {
    double solde;
    double plafond;

    CodeSecret codeSecret;

    private void controlPreOperation(double montant) throws purseBloqueException, MontantNegatifInterditException {
        if(codeSecret.isBlocked()) throw new purseBloqueException();
        if(montant < 0) throw new MontantNegatifInterditException();
    }

    public Purse(double plafond, CodeSecret codeSecret) {
        this.plafond = plafond;
        this.codeSecret = codeSecret;
    }

    public double getSolde() {
        return solde;
    }

    public void credit(double montant) throws PlafondDepasseInterditException , MontantNegatifInterditException , purseBloqueException{
        controlPreOperation(montant);
        if(solde + montant > this.plafond ) throw new PlafondDepasseInterditException();
        solde += montant;
    }

    public void debit(double montant, String code) throws SoldeNegatifInterditException, MontantNegatifInterditException, CodeSecretErroneException, purseBloqueException {
        controlPreOperation(montant);
        if(!codeSecret.verifierCode(code)) throw new CodeSecretErroneException();
        if(montant >solde) throw new SoldeNegatifInterditException();
        solde -= montant;
    }
}
