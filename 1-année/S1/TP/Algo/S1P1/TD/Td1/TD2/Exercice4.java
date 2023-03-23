import java.util.*;

class Exercice4 {

    static final Scanner input =new Scanner (System.in);

public static void main(String[]args){
        double Celsius;
        double Fahrenheit;
        System.out.println("Entrer temperature en Celsius :");
        Celsius = input.nextDouble() ;
        Fahrenheit = (9/5)*Celsius+32;
        System.out.println("La température en Fahrenheit :" + Fahrenheit);
    }
}