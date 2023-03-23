public class TestInterrupteur {
    public static void main(String[] args) {
        Lampe lampe = new Lampe();
        Interrupteur interrupteur = new Interrupteur(lampe);

        System.out.println("La lampe est allumée ? " + lampe.estAllumee()); // La lampe est allumée ? false
        System.out.println("L'interrupteur est allumé ? " + interrupteur.estAllume()); // L'interrupteur est allumé ? false

        interrupteur.allumer();

        System.out.println("La lampe est allumée ? " + lampe.estAllumee()); // La lampe est allumée ? true
        System.out.println("L'interrupteur est allumé ? " + interrupteur.estAllume()); // L'interrupteur est allumé ? true

        interrupteur.eteindre();

        System.out.println("La lampe est allumée ? " + lampe.estAllumee()); // La lampe est allumée ? false
        System.out.println("L'interrupteur est allumé ? " + interrupteur.estAllume()); // L'interrupteur est allumé ? false
    }
}