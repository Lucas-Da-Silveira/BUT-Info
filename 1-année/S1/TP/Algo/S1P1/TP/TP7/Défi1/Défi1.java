class Défi1{

    public static void main(String []args){
        StdDraw.enableDoubleBuffering();
        int n = Integer.parseInt(args[0]);

        StdDraw.setXscale(-0.5 , n-0.5);
        StdDraw.setYscale(-0.5 , n-0.5);

        for(int y=0 ; y < n; y++){
            for(int x=0 ; x < n; x++){
                if((x + y)% 2 == 0){
                    StdDraw.setPenColor(StdDraw.RED);
                }else {
                    StdDraw.setPenColor(StdDraw.BLACK);
                }
            StdDraw.filledSquare(double x,double y , 0.5);
            }
       
        }
        
    }    
}