import java.util.*;

public class Défi1 {
    public static void main(String[] args) {
        StdDraw.setXscale(-1.1, 1.1);
        StdDraw.setYscale(-1.1, 1.1);
        StdDraw.enableDoubleBuffering();

        /*
        int n = Integer.parseInt(args[0]);
        int tt = Integer.parseInt(args[1]);

        if (n <= 1 || tt <= 0) {
            System.out.println("Invalid arguments");
            return;
        }

        
        StdDraw.circle(0, 0, 1);

        double angle = (2.0*Math.PI) / n;
        double[][] points = new double[n][2];

        preCalc(angle, points);

        affiche2DTab(points);

        for (int i = 0; i < points.length; i++) {
            drawTT(i, n, tt, points);
        }

        repPoints(points);
        */

        // animTTn();

        animTTn();
    }

     /**
     * Place les points (sur un cercle) espacés equitablement de l'angle donné en paramètre
     * @param angle l'angle entre deux points, exprimé en radian
     * @param points tableau où sera rempli toutes les coordonées des points
     */
    public static void preCalc(double angle, double[][] points) {
        int counter = 0;
        double i = 0;

        while (counter < points.length && i < 2.0*Math.PI) {
            double x = Math.cos(i);
            double y = Math.sin(i);

            if (points.length <= 33) {
                StdDraw.filledCircle(x, y, .02);
            }

            points[counter][0] = x;
            points[counter][1] = y;

            i += angle;
            counter++;
        }
    }

    public static void drawTT(int i, int n, double tt, double[][] points) {
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.line(points[i][0], points[i][1], points[((int)tt*i)%n][0], points[((int)tt*i)%n][1]);
    }

    public static void repPoints(double[][] points) {
        double ESPACEMENT = 1.07;
        if (points.length > 33) return;
        for (int i = 0; i < points.length; i++) {
            StdDraw.text(points[i][0] * ESPACEMENT, points[i][1] * ESPACEMENT, Integer.toString(i));
        }
    }

    public static void animTTn() {
        double tt = 3.0;

        for (int n = 10; n <= 200; n++) {
            StdDraw.clear(StdDraw.WHITE);

            StdDraw.circle(0, 0, 1);

            double angle = (2.0*Math.PI) / n;
            double[][] points = new double[n][2];

            preCalc(angle, points);

            for (int i = 0; i < points.length; i++) {
                drawTT(i, n, tt, points);
            }

            repPoints(points);

            StdDraw.show();
            StdDraw.pause(100);
        }
    }
}
    