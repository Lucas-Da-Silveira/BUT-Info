import java.util.*;
import static java.lang.Math.*;



class CarréCube{
    public static void main(String[] args){

        int nombre = 0;

        for (int i=0; i < sqrt(2017); i++){
            for (int j= 0; j < sqrt(2017); j++){
                nombre = i*i + j*j*j;
                if (nombre < 2017){
                    System.out.println(nombre +"=" +i+"²" +"+"+ j+"³");
                }
            }

        }


    }
}