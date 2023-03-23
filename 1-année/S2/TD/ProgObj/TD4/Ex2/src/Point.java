public class Point {
    
    private int x;
    private int y;
    private int z;

    public Point(int x) {
        this.x = x;
        y = z = 0;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        z = 0;
    }

    public Point(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
