import java.util.*;

class Horloge{

    public static void main (String [] args){
        StdDraw.setScale(-1.5, 1.5);
        StdDraw.setPenRadius(0.05);

        double rayon = 1.5;

        int hours = Calendar.getInstance().get(Calendar.HOUR);
        int minutes = Calendar.getInstance().get(Calendar.MINUTE) ;
        int seconds = Calendar.getInstance().get(Calendar.SECOND) ;



        /**
        * Affiche les 12 points d'une horloge
        */
        double [][] points = new double [12][2];
        for (int i =0 ; i<12; i++){
            double x = Math.cos(2 * Math.PI * i / 12);
            double y = Math.sin(2 * Math.PI * i / 12);
            StdDraw.point(x, y);
        }

        

        /**
        * Affiche l'aiguille des heures 
        */
        double angleheure=(hours*((2*Math.PI)/12.0)-(Math.PI/2.0));
        double xheure = (0.4*rayon*Math.cos(angleheure));
        double yheure = (0.4*rayon*Math.sin(angleheure));
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setPenRadius(0.02);
        StdDraw.line(0.0 , 0.0, xheure , yheure);



        /**
        * Affiche l'aiguille des minutes 
        */
        double angleminute=(minutes*((Math.PI)/60.0)-(2*Math.PI/2.0));
        double xminutes = (0.5*rayon*Math.cos(angleminute));
        double yminutes = (0.5*rayon*Math.sin(angleminute));
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(0.02);
        StdDraw.line(0.0 , 0.0, xminutes , yminutes);



        /**
        * Affiche l'aiguille des secondes 
        */
        double angleseconde=(seconds*((Math.PI)/60.0)-(2*Math.PI/2.0));
        double xseconde = (0.6*rayon*Math.cos(angleseconde));
        double yseconde = (0.6*rayon*Math.sin(angleseconde));
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.setPenRadius();
        StdDraw.line(0.0 , 0.0, xseconde , yseconde);



        /** 
        * Fait tourner l'horloge((-)
        */
        while(true){
            seconds=seconds+1;
            if(seconds>60)
            {
                minutes=minutes+1;
                seconds=1;
                if(minutes>60)
                {
                    minutes=1;
                    hours=hours+1;
                    if(hours>12)
                    {
                        hours=1;
                    }
                }
            }


            StdDraw.show();
            StdDraw.pause(1000);

        }




        

    }
}