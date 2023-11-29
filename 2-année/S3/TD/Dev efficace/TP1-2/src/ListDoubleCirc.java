public class ListDoubleCirc {

    public CellDouble head;
    public int size;

    public ListDoubleCirc() {
        head = null;
        size = 0;
    }

    public CellDouble find(int value) {
        CellDouble c = head;
        while((c != null) && (c.value != value)){
            c = c.next;
        }
        return c;
    }

    public CellDouble get(int index) {
        // pour éviter de tout parcourir, on peut tester si l'indice
        // est avant ou après la moitié de la liste. S'il est avant,
        // on explore la liste grâce à next, et sinon grâce à prev
        CellDouble c = head;
        int i = 0;
        while((c != null) && (i < index)){
            c = c.next;
            i++;
        }
        return c;
    }

    public CellDouble append(int value) {
        // pas besoin de trouver la dernière cellule : on y a accès
        // grâce à prev de la tête. Seul cas particulier, la liste est vide
        CellDouble c = null;
        CellDouble newCellD = new CellDouble(value);
        if (head == null) {
            head = newCellD;
            head.next = head;
            head.prev = head;
        } else {
            c = head.prev;
            c.next = newCellD;
            newCellD.prev = c;
            newCellD.next = head;
            head.prev = newCellD;
        }
        size++;
        return newCellD;
    }

    public CellDouble prepend(int value) {
        // pas compliqué : insertion en tête = insertion en fin
        // puis déplacement de la tête
        CellDouble newCellD = append(value);
        head = newCellD;
        return newCellD;
    }


    public CellDouble insert(int value, int index) {
        // on a get() pour trouver la cellule du point d'insertion
        // Seul cas particulier, la liste est vide
        CellDouble c = null;
        CellDouble newCellD = new CellDouble(value);
        if (head == null) {
            head = newCellD;
            head.next = head;
            head.prev = head;
        } else {
            c = get(index);
            if (c == null) {
                c = head.prev;
            }
            c.next.prev = newCellD;
            newCellD.next = c.next;
            c.next = newCellD;
            newCellD.prev = c;
        }
        return c;
    }

    public CellDouble replace(int value, int index) {
        // utiliser get()
        CellDouble c = get(index);
        if (c != null) {
            c.value = value;
        }
        return c;
    }

    public CellDouble removeAt(int index) {
        // utiliser get()
        CellDouble c = get(index);
        if (c != null) {
            c.prev.next = c.next;
            c.next.prev = c.prev;
            if (c == head) {
                head = c.next;
            }
            size--;
        }
        return c;
    }

    public CellDouble remove(int value) {
        // utiliser find
        CellDouble c = find(value);
        if (c != null) {
            c.prev.next = c.next;
            c.next.prev = c.prev;
            if (c == head) {
                head = c.next;
            }
            size--;
        }
        return c;
    }

    public void print() {
        CellDouble c = head;
        if (c != null) {
            do {
                System.out.print(c.value + " ");
                c = c.next;
            } while (c != head);
        }
        System.out.println();
    }
}
