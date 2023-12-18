public class ListSimple {
    public Cell head;
    public int size;

    public ListSimple(){
        head = null;
        size = 0;
    }

    public Cell find(int value){
        Cell c = head;
        while((c != null) && (c.value != value)){
            c = c.next;
        }
        return c;
    }

    public Cell get(int index){
        Cell c = head;
        int i = 0;

        if(index < 0){ c =null;}
        while((c != null) && (i < index)){
            c= c.next;
            i++;
        }
        return c;
    }

    public Cell append(int value){
        Cell c = null;
        Cell newCell = new Cell(value);

        if(size == 0){
            head=newCell;
        }
        else{
            c = get(size -1);
            c.next = newCell;
        }
        size += 1;
        return newCell;
    }

    public Cell insert(int value, int index){
        Cell c = null;
        Cell newCell = new Cell(value);

        if(index > size){index = size;}
        if(index < 0){index = 0;}

        if (size == 0){
            head = newCell;
        }
        else if(index == 0){
            newCell.next = head;
            head = newCell;
        }
        else{
            c = get(index -1);
            newCell.next = c.next;
            c.next = newCell;
        }
        size += 1;
        return newCell;
    }

    public Cell replace(int value, int index){
        if ((index < 0) || (index >= size)) return null;

        Cell c = get(index);
        c.value = value;

        return c;
    }

    public Cell removeAt(int index) {
        Cell c = null;
        Cell p = null;

        if ((index < 0) || (index >= size)) return null;

        if (index == 0) {
            c = head;
            head = head.next;
        } else {
            p = get(index - 1);
            c = p.next;
            p.next = c.next;
        }
        size -= 1;
        return c;
    }

    public Cell remove(int value){
        Cell c = head;
        Cell p = null; //Pour garder la cellule précédente

        while((c != null) && c.value != value){
            p = c;
            c = c.next;
        }
        if (c!=null){
            if(c == head){
                head = c.next;
            }
            else{
                p.next = c.next;

            }
            size -=1;
        }
        return c;
    }

    public void print(){
        Cell c = head;
        while(c != null){
            System.out.println(c.value);
            c = c.next;
        }
    }

}


