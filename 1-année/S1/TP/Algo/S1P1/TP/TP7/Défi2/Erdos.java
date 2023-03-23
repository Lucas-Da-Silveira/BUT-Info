/**
* DA SILVEIRA Lucas
*/

class Erdos{

  

    public static boolean tireProba(double p){   
        double ale = Math.random();
        if (ale < p ){
            return true;
        }
        return false;
        
    }  


    public static void main (String [] args){
        int cont =0;
        int N = Integer.parseInt(args[0]);
        double p = Double.parseDouble(args[1]);

        if(p>1 || p<0){
            System.out.println("Impossible");
            return;
        }

        

        StdDraw.setScale(-1.5, 1.5);
        StdDraw.setPenRadius(0.05);

        double[][] points = new double[N][2];
        for (int i = 0; i < N; i++){
            double x = Math.cos(2 * Math.PI * i / N);
            double y = Math.sin(2 * Math.PI * i / N);
            StdDraw.point(x, y);
        

        points[i][0] = x;
        points[i][1] = y;
        
        }
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.setPenRadius();

        for (int i = 0; i < N; i++){
            for (int j = i+1; j < N; j++){
                if(tireProba(p)){
                    StdDraw.line(points[i][0], points[i][1], points[j][0], points[j][1]);
                    cont++;
                }

            }
        }
        System.out.println(cont +""+"segments");
            
    }
} 