class TestVoiture{

    public static void main(String[] args){
        Voiture v1 = new Voiture();
        System.out.println("Vitesse de la voiture: " + v1.getVitesse());

        v1.accélérer();
        System.out.println("Vitesse de la voiture: " + v1.getVitesse());

        v1.accélérer(5);
        System.out.println("Vitesse de la voiture: " + v1.getVitesse());

        v1.ralentir();
        System.out.println("Vitesse de la voiture: " + v1.getVitesse());

        Voiture v2 = new Voiture();
        v2.voiture(10);
        System.out.println("Vitesse de la voiture: " + v2.getVitesse());

        v2.accélérer();
        System.out.println("Vitesse de la voiture: " + v2.getVitesse());

        v2.accélérer(5);
        System.out.println("Vitesse de la voiture: " + v2.getVitesse());

        v2.ralentir();
        System.out.println("Vitesse de la voiture: " + v2.getVitesse());

    }
}