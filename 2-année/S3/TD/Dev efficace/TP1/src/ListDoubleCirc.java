public class ListDoubleCirc {

    public CellDouble head;
    public int size;

    public ListDoubleCirc() {
        head = null;
        size = 0;
    }

    public CellDouble find(int value) {
        // quasi identique à l'exo 1, excepté le test d'arrêt
        CellDouble c = head;
        while ((c != null) && (c.value != value)) {
            c = c.next;
        }
        return c;
    }

    public CellDouble get(int index) {
        // pour éviter de tout parcourir, on peut tester si l'indice
        // est avant ou après la moitié de la liste. S'il est avant,
        // on explore la liste grâce à next, et sinon grâce à prev


    }

    public CellDouble append(int value) {
        // pas besoin de trouver la dernière cellule : on y a accès
        // grâce à prev de la tête. Seul cas particulier, la liste est vide
    }

    public CellDouble prepend(int value) {
        // pas compliqué : insertion en tête = insertion en fin
        // puis déplacement de la tête
    }


    public CellDouble insert(int value, int index) {
        // on a get() pour trouver la cellule du point d'insertion
        // Seul cas particulier, la liste est vide
    }

    public CellDouble replace(int value, int index) {
        // utiliser get()
    }

    public CellDouble removeAt(int index) {
        // utiliser get()
    }

    public CellDouble remove(int value) {
        // utiliser find
    }

}