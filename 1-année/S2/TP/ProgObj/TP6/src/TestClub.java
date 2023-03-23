import java.util.ArrayList;

public class TestClub {

    public static void main(String[] args) {

        Club c1 = new Club();
        Club c2 = new Club();

        c1.init();
        c2.init();

        System.out.println(c1.equals(c2));
        c1.setNomClub("Club 1");
        c2.setNomClub("Club 2");
    }
    
}
