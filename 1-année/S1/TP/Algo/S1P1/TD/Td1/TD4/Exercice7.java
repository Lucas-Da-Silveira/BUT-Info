import java.util.Scanner;

class Exercice7{
    static Scanner myObj = new Scanner(System.in);

    public static void main(String[]args){

        double a;
        double b;
        double c;
        double x0;
        double x1;
        double x2;
        double delta;

        System.out.println("Entrez une valeur de a");
        a=myObj.nextDouble();
        System.out.println("Entrez une valeur de b");
        b=myObj.nextDouble();
        System.out.println("Entrez une valeur de c");
        c=myObj.nextDouble();

        delta=b*b-4*a*c;

        if (delta<0)
        {
            System.out.println("l'équation n'a pas de solution réelle");
        }
        else if(delta==0)  
        {
            x0=-b/(2*a);
        System.out.println("l'équation a une unique solution :"+x0);
        }
        else 
        {
            x1=-b-Math.sqrt(delta)/(2*a);
            x2=-b+Math.sqrt(delta)/(2*a);
        System.out.println("l'equation a deux solution :"+x1  +x2);
        }
    }
}