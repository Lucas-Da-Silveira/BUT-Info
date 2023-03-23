import java.util.Scanner;

class Echange2{
    static Scanner myObj = new Scanner(System.in);

    public static void main(String[]args){
        
        double valA=0;
        double valB=0;
        double temp=0;

        System.out.println("Entrez les valeurs pour A :");
        valA = myObj.nextDouble();
        System.out.println("Entrez les valeurs pour B :");
        valB = myObj.nextDouble();
        System.out.println("valA"+valA+", valB"+valB);
        temp=valB;
        valB = valA;
        valA = temp;
        System.out.println("valA"+valA+", valB"+valB);
    }
}