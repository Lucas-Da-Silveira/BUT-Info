import java.util.Scanner;

class Bonjour{
    static final Scanner myObj = new Scanner(System.in);
    public static void main(String arg[]){
      
      String prenom;
      System.out.print("entrez votre prenom :");
      prenom= myObj.next();
      System.out.println("Bonjour " + prenom + " je suis heureux de te connaitre");
    }
}
