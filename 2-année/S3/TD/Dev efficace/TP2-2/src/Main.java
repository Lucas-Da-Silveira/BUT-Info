import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int nbElements=5;

        ListOfWords lWords=new ListOfWords();
        ArrayList<String> l= lWords.randomSelect(nbElements);
        for(String s : l){
            System.out.println(s);
        }
        long start=System.currentTimeMillis();

        l.add("qlsjhrgblhqbfsbf");
        l.add("qlksjjfbslqkjbejfblsbfbs");

        ArrayList<String> lfound = lWords.find(l);
        long end=System.currentTimeMillis();
        long timeElapsed = end - start;
        for(String s : lfound){
            System.out.println(s);
        }
        System.out.println("time with List "+timeElapsed);
    }
}