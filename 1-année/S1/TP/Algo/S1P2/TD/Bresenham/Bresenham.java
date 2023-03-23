import java.awt.Color;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;



class Bresenham{


    public static void trace(List<int[]> liste, Color color){
        StdDraw.setPenColor(color);
        for (int[] point : liste){
            StdDraw.point(point[0], point[1]);
        }
    }
    


    public static List<int[]> cercle(int xc, int yc, int r){
        List<int[]> List = new ArrayList<>();
        int x = 0;
        int y = r;
        int _4m = 5 - 4*y ;
        while(y >= x){
            List.add(new int[]{xc +y,yc +x}); //Q0
            List.add(new int[]{xc +x,yc +y}); //Q1
            List.add(new int[]{xc -x,yc +y}); //Q2
            List.add(new int[]{xc -y,yc -x}); //Q3
            List.add(new int[]{xc -y,yc +x}); //Q4
            List.add(new int[]{xc -x,yc -y}); //Q5
            List.add(new int[]{xc +x,yc -y}); //Q6
            List.add(new int[]{xc +y,yc -x}); //Q7
            if (_4m>0){
                _4m -= 8*y;
                y--;
            }
            _4m += 8*x + 12;
            x++;
        }return List;
    }

    public static void main(String[] args) {
        final int SIZE = 512;
        // 1 pixel par unité
        StdDraw.setCanvasSize(SIZE, SIZE);
        StdDraw.setXscale(-SIZE/2, SIZE/2);
        StdDraw.setYscale(-SIZE/2, SIZE/2);

        List<int[]> lCercle1;
        List<int[]> lCercle2;
        lCercle1 = cercle(0,0,120);
        lCercle2 = cercle(100,50,80);

        trace(lCercle1, StdDraw.BLUE);
        trace(lCercle2, StdDraw.GREEN);
        
    }
}