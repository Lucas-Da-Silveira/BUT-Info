package izly;

public class Purse {
    double solde;
    double plafond;
    int nbOperationMax;
    CodeSecret codeSecret;

    private void controlPreOperation(double montant) throws TransactionRejeterException {
        if(nbOperationMax<=0) throw new NoOpMAxAtteindException();
        if(codeSecret.isBlocked()) throw new purseBloqueException();
        if(montant < 0) throw new MontantNegatifInterditException();
    }

    public Purse(double plafond, CodeSecret pinCode, int nbOperationMax) {
        this.plafond = plafond;
        this.codeSecret = pinCode;
        this.nbOperationMax = nbOperationMax;
    }

    public double getSolde() {
        return solde;
    }

    public void credit(double montant) throws TransactionRejeterException{
        controlPreOperation(montant);
        if(solde + montant > this.plafond ) throw new PlafondDepasseInterditException();
        solde += montant;
        nbOperationMax--;
    }

    public void debit(double montant, String code) throws TransactionRejeterException, CodeBloqueException {
        controlPreOperation(montant);
        if(!codeSecret.verifierCode(code)) throw new CodeSecretErroneException();
        if(montant >solde) throw new SoldeNegatifInterditException();
        solde -= montant;
        nbOperationMax--;
    }
}
