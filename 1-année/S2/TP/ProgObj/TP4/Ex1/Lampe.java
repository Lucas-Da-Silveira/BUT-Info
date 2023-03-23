public class Lampe {
    private boolean allumee;

    public Lampe() {
        this.allumee = false;
    }

    public void allumer() {
        this.allumee = true;
        System.out.println("La lampe est allumée");
    }

    public void eteindre() {
        this.allumee = false;
        System.out.println("La lampe est éteinte");
    }

    public boolean estAllumee() {
        return this.allumee;
    }
}