import java.util.*;

class Exercice1{
    static final Scanner input = new Scanner(System.in);

    public static void main(String[]args){

        float Fahrenheit;
        double Celcius;
        
        System.out.println("Etrez la température en fahrenheit :");
        Fahrenheit=input.nextFloat();
        Celcius=(double) 5/9*(Fahrenheit-32);
        System.out.println("Voici la température en Celcius :"+ Celcius);

    }
}