public class Main {
    public static void main(String[] args) {
        ListDoubleCirc liste = new ListDoubleCirc();
        liste.append(20); // liste vide -> insertion en tête
        liste.insert(10, -5); // insertion en tête
        liste.insert(30, 7); // insertion en fin
        liste.append(50); // ajout en fin
        liste.insert(40, 3); // insertion en 3*/
        liste.print(); // affiche 10 20 30 40 50
        liste.remove(12); // ne fait rien
        liste.remove(10); // enlève la valeur 10 (la tête)
        liste.removeAt(-2); // ne fait rien
        liste.removeAt(22); // ne fait rien
        liste.removeAt(2); // enleve la valeur en 2 (i.e. 40)
        liste.print(); // affiche 20 30 50*/
        CellDouble c = liste.get(-1); // accès en dehors -> renvoie null
        if (c != null) {
            System.out.println("pb with get()");
        }
        c = liste.get(99); // accès en dehors -> renvoie null
        if (c != null) {
            System.out.println("pb with get()");
        }
        c = liste.get(1); // accès indice 1 (i.e 30)
        if (c.value != 30) {
            System.out.println("pb with get()");
        }
        c = liste.find(99); // value inexsitante -> renvoie null
        if (c != null) {
            System.out.println("pb with find()");
        }
        c = liste.find(20);
        if (c.value != 20) {
            System.out.println("pb with find()");
        }
        c = liste.find(50);
        if (c.value != 50) {
            System.out.println("pb with find()");
        }
    }
}