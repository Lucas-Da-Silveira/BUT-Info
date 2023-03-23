public class TestFootball {
    public static void main(String[] args) {
        Club c1 = new Club("");
        Club c2 = new Club("");
        c1.init();
        c2.init();
        c1.faireEquipe();
        c2.faireEquipe();
        Match m = new Match(c1, c2);
        m.afficher();



    }
}