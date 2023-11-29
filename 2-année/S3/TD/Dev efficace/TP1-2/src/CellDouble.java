public class CellDouble {

    public int value;
    public CellDouble prev;
    public CellDouble next;

    public CellDouble(int value) {
        this.value = value;
        prev = null;
        next = null;
    }
}