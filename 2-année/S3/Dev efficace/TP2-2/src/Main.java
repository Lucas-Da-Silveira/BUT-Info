import java.util.ArrayList;

public class Main {

    //List

    /*public static void main(String[] args) {
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
    }*/


    //Hash

    /*public static void main(String[] args) throws Exception {
        int nbElements=10000;

        ListOfWords lWords=new ListOfWords();
        ArrayList<String> l= lWords.randomSelect(nbElements);
        for(String s : l){
            //System.out.println(s);
        }
        long start=System.currentTimeMillis();

        l.add("qlsjhrgblhqbfsbf");
        l.add("qlksjjfbslqkjbejfblsbfbs");

        ArrayList<String> lfound = lWords.find(l);
        long end=System.currentTimeMillis();
        long timeElapsed = end - start;

        for(String s : lfound){
            //System.out.println(s);
        }
        System.out.println("time with List "+timeElapsed);


        HashOfWords hWords=new HashOfWords();

        start=System.currentTimeMillis();
        lfound = hWords.findValuesList(l);
        end=System.currentTimeMillis();
        timeElapsed = end - start;
        System.out.println("time with HashMap values List "+timeElapsed);


        start=System.currentTimeMillis();
        lfound = hWords.findValuesToSet(l);
        end=System.currentTimeMillis();
        timeElapsed = end - start;
        System.out.println("time with HashMap values converted to Set "+timeElapsed);


        start=System.currentTimeMillis();
        lfound = hWords.findKeys(l);
        end=System.currentTimeMillis();
        timeElapsed = end - start;
        for(String s : lfound){
            //System.out.println(s);
        }
        System.out.println("time with HashMap keys "+timeElapsed);
    }*/

    //Test comparaison des tables de hachage

    public static void main(String[] args) throws Exception {
        int nbElements=10000;

        ListOfWords lWords=new ListOfWords();
        ArrayList<String> l= lWords.randomSelect(nbElements);
        for(String s : l){
            //System.out.println(s);
        }
        long start=System.currentTimeMillis();
        ArrayList<String> lfound = lWords.find(l);
        long end=System.currentTimeMillis();
        long timeElapsed = end - start;

        for(String s : lfound){
            //System.out.println(s);
        }
        System.out.println("time with List "+timeElapsed);


        for (int i=0;i<3;i++) {


            HashOfWords hWords=new HashOfWords(i);
            switch(i) {
                case 0: System.out.println("HashMap"); break;
                case 1: System.out.println("TreeMap"); break;
                case 2: System.out.println("LinkedHashMap"); break;
            }

            start=System.currentTimeMillis();
            lfound = hWords.findValuesList(l);
            end=System.currentTimeMillis();
            timeElapsed = end - start;
            System.out.println("time with HashMap values List "+timeElapsed);


            start=System.currentTimeMillis();
            lfound = hWords.findValuesToSet(l);
            end=System.currentTimeMillis();
            timeElapsed = end - start;
            System.out.println("time with HashMap values converted to Set "+timeElapsed);


            start=System.currentTimeMillis();
            lfound = hWords.findKeys(l);
            end=System.currentTimeMillis();
            timeElapsed = end - start;
            /*for(String s : lfound){
                System.out.println(s);
            }*/
            System.out.println("time with HashMap keys "+timeElapsed);
        }

    }

}