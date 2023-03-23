public class TestFarWest  {

    public static void main(String[] args) {
        Dame d1 = new Dame("Marie");
        Dame d2 = new Dame("Jeanne");

        Cowboy c1 = new Cowboy("Jack", "cool", 10);
        Cowboy c2 = new Cowboy("John", "gentil", 5);

        Brigand b1 = new Brigand("Jules", "méchant", 3, 10000);
        Brigand b2 = new Brigand("Jean", "laid", 1, 5000);


        System.out.println("=== Test de la classe Humain ===");
        Humain h1 = new Humain("Bob", "eau");
        System.out.println(h1.getNom());
        System.out.println(h1.getBoisson());
        h1.setBoisson("bière");
        System.out.println(h1.getBoisson());
        h1.parle("Salut !");
        h1.sePresenter();

        System.out.println("=== Test de la classe Dame ===");
        System.out.println(d1.getNom());
        System.out.println(d1.estLibre());
        d1.kidnapper();
        System.out.println(d1.estLibre());
        d1.liberer();
        System.out.println(d1.estLibre());

        System.out.println("=== Test de la classe Brigand ===");
        System.out.println(b1.getNom());
        System.out.println(b1.getLook());
        System.out.println(b1.getNbDamesEnlevees());
        b1.sePresenter();
        b1.kidnapper(d1);
        b1.kidnapper(d2);

        System.out.println("=== Test de la classe Cowboy ===");
        System.out.println(c1.getNom());
        System.out.println(c1.getAdjectif());
        System.out.println(c1.getPopularite());
        c1.sePresenter();
        c1.liberer(d1);
        c1.liberer(d2);
        c1.liberer(d1);
        c1.liberer(d2);


    }
        
}
