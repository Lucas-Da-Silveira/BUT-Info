public class Interrupteur {
    private boolean allume;
    private Lampe lampe;

    public Interrupteur(Lampe lampe) {
        this.allume = false;
        this.lampe = lampe;
    }

    public void allumer() {
        if (!this.allume) {
            this.allume = true;
            this.lampe.allumer();
        }
    }

    public void eteindre() {
        if (this.allume) {
            this.allume = false;
            this.lampe.eteindre();
        }
    }

    public boolean estAllume() {
        return this.allume;
    }
}


