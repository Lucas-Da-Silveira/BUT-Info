import java.util.*;

class TestPerf {

    public static void main(String[] args) {
        ListSimple myListSimple = new ListSimple();
        ListDoubleCirc myListDouble = new ListDoubleCirc();
        List<Integer> arrayList = new ArrayList<Integer>();
        List<Integer> linkedList = new LinkedList<Integer>();

        if (args.length != 1) {
            System.err.println("usage: java TestPerf nb_cell");
            System.exit(1);
        }
        int nbCell = Integer.parseInt(args[0]);

        /* liste simplement chaînée */
        System.out.print("liste simple : insertion début ... ");
        long time = Calendar.getInstance().getTimeInMillis();
        for (int i = 0; i < nbCell; i++) {
            myListSimple.insert(i + 1, 0);
        }
        time = Calendar.getInstance().getTimeInMillis() - time;
        System.out.println((time / 1000.0) + "s");

        myListSimple = new ListSimple();
        System.out.print("liste simple : insertion milieu ... ");
        time = Calendar.getInstance().getTimeInMillis();
        for (int i = 0; i < nbCell; i++) {
            myListSimple.insert(i + 1, i / 2);
        }
        time = Calendar.getInstance().getTimeInMillis() - time;
        System.out.println((time / 1000.0) + "s");

        myListSimple = new ListSimple();
        System.out.print("liste simple : insertion fin ... ");
        time = Calendar.getInstance().getTimeInMillis();
        for (int i = 0; i < nbCell; i++) {
            myListSimple.append(i + 1);
        }
        time = Calendar.getInstance().getTimeInMillis() - time;
        System.out.println((time / 1000.0) + "s");

        /* liste doublement chaînée */
        System.out.print("liste double : insertion début ... ");
        time = Calendar.getInstance().getTimeInMillis();
        for (int i = 0; i < nbCell; i++) {
            myListDouble.prepend(i + 1);
        }
        time = Calendar.getInstance().getTimeInMillis() - time;
        System.out.println((time / 1000.0) + "s");

        myListDouble = new ListDoubleCirc();
        System.out.print("liste double : insertion milieu ... ");
        time = Calendar.getInstance().getTimeInMillis();
        for (int i = 0; i < nbCell; i++) {
            myListDouble.insert(i + 1, i / 2);
        }
        time = Calendar.getInstance().getTimeInMillis() - time;
        System.out.println((time / 1000.0) + "s");

        myListDouble = new ListDoubleCirc();
        System.out.print("liste double : insertion fin ... ");
        time = Calendar.getInstance().getTimeInMillis();
        for (int i = 0; i < nbCell; i++) {
            myListDouble.append(i + 1);
        }
        time = Calendar.getInstance().getTimeInMillis() - time;
        System.out.println((time / 1000.0) + "s");

        /* LinkedList de l'API Java */
        System.out.print("linked list de l'API java : insertion début ... ");
        time = Calendar.getInstance().getTimeInMillis();
        for (int i = 0; i < nbCell; i++) {
            linkedList.add(0, i + 1);
        }
        time = Calendar.getInstance().getTimeInMillis() - time;
        System.out.println((time / 1000.0) + "s");

        linkedList = new LinkedList<Integer>();
        System.out.print("linked list de l'API java : insertion milieu ... ");
        time = Calendar.getInstance().getTimeInMillis();
        for (int i = 0; i < nbCell; i++) {
            linkedList.add(i / 2, i + 1);
        }
        time = Calendar.getInstance().getTimeInMillis() - time;
        System.out.println((time / 1000.0) + "s");

        linkedList = new LinkedList<Integer>();
        System.out.print("linked list de l'API java : insertion fin ... ");
        time = Calendar.getInstance().getTimeInMillis();
        for (int i = 0; i < nbCell; i++) {
            linkedList.add(linkedList.size(), i + 1);
        }
        time = Calendar.getInstance().getTimeInMillis() - time;
        System.out.println((time / 1000.0) + "s");

        /* ArrayList de l'API Java */
        System.out.print("array list de l'API java : insertion début ... ");
        time = Calendar.getInstance().getTimeInMillis();
        for (int i = 0; i < nbCell; i++) {
            arrayList.add(0, i + 1);
        }
        time = Calendar.getInstance().getTimeInMillis() - time;
        System.out.println((time / 1000.0) + "s");

        arrayList = new ArrayList<Integer>();
        System.out.print("array list de l'API java : insertion milieu ... ");
        time = Calendar.getInstance().getTimeInMillis();
        for (int i = 0; i < nbCell; i++) {
            arrayList.add(i / 2, i + 1);
        }
        time = Calendar.getInstance().getTimeInMillis() - time;
        System.out.println((time / 1000.0) + "s");

        arrayList = new ArrayList<Integer>();
        System.out.print("array list de l'API java : insertion fin ... ");
        time = Calendar.getInstance().getTimeInMillis();
        for (int i = 0; i < nbCell; i++) {
            arrayList.add(i + 1);
        }
        time = Calendar.getInstance().getTimeInMillis() - time;
        System.out.println((time / 1000.0) + "s");
    }
}