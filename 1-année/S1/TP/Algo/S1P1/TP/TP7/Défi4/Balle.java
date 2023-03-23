class Balle{
    static public void main (String [] args){
        StdDraw.setScale(-1.5, 1.5);

     


        /**
        * Coordonnée de la balle
        */
        double xballe = 0.480;
        double yballe = 0.860;


        /** 
        * Test vélocité 
        */
        double xvelocite = 0.015;
        double yvelocite = 0.023;

        double rayon = 0.05;

        while(true){

            if(Math.abs(xballe - xvelocite) > 1.0 - rayon) xvelocite = -xvelocite ;
            if(Math.abs(yballe - yvelocite) > 1.0 - rayon) yvelocite = -yvelocite ;

            xballe = xballe + xvelocite;
            yballe = yballe + yvelocite;

            StdDraw.clear(StdDraw.BLACK);


            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.filledCircle(xballe , yballe, rayon);

            StdDraw.show();

            StdDraw.pause(20);
        }



    }
}