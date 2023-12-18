public class ListDoubleCirc {

    public CellDouble head;
    public int size;

    public ListDoubleCirc() {
        head = null;
        size = 0;
    }

    public CellDouble find(int value) {
        CellDouble current = head;

        while (current != null && current.value != value) {
            current = current.next;
            if (current == head) {
                return null;
            }
        }

        return current;
    }

    public CellDouble get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        CellDouble current = head;

        if (index < size / 2) {
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }

        return current;
    }


    public CellDouble append(int value) {
        CellDouble newCell = new CellDouble(value);

        if (head == null) {
            head = newCell;
            head.next = head;
            head.prev = head;
        } else {
            CellDouble last = head.prev;
            last.next = newCell;
            newCell.prev = last;
            newCell.next = head;
            head.prev = newCell;
        }

        size++;
        return newCell;
    }

    public CellDouble prepend(int value) {
        CellDouble newCell = new CellDouble(value);

        if (head == null) {
            head = newCell;
            head.next = head;
            head.prev = head;
        } else {
            CellDouble last = head.prev;
            last.next = newCell;
            newCell.prev = last;
            newCell.next = head;
            head.prev = newCell;
            head = newCell;
        }

        size++;
        return newCell;
    }

    public CellDouble insert(int value, int index) {
        if (index < 0) {
            return prepend(value);

        } else if (index >= size) {
            return append(value);

        } else {
            CellDouble current = get(index);
            CellDouble newCell = new CellDouble(value);

            newCell.next = current;
            newCell.prev = current.prev;
            current.prev.next = newCell;
            current.prev = newCell;

            size++;
            return newCell;
        }
    }


    public CellDouble replace(int value, int index) {
        CellDouble cellToReplace = get(index);

        if (cellToReplace != null) {
            cellToReplace.value = value;
        }

        return cellToReplace;
    }

    public CellDouble removeAt(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        CellDouble removedCell;

        if (size == 1) {

            removedCell = head;
            head = null;

        } else if (index == 0) {
            removedCell = head;
            head = head.next;
            head.prev = removedCell.prev;
            removedCell.prev.next = head;

        } else {
            CellDouble current = get(index);
            removedCell = current;

            current.prev.next = current.next;
            current.next.prev = current.prev;
        }

        size--;
        return removedCell;
    }

    public CellDouble remove(int value) {
        CellDouble cellToRemove = find(value);

        if (cellToRemove != null) {
            int index = 0;
            CellDouble current = head;
            while (current != cellToRemove) {
                current = current.next;
                index++;
            }

            removeAt(index);
        }

        return cellToRemove;
    }

    public void print() {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        CellDouble current = head;
        do {
            System.out.print(current.value + " ");
            current = current.next;
        } while (current != head);

        System.out.println();
    }


}
