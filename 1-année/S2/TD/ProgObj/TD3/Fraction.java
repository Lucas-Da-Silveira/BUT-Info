import java.util.Scanner;

public class Fraction{
    private int numerateur;
    private int dénominateur;

    public Fraction(int n,int d){
        this.numerateur = n;
        this.dénominateur = d;
    }

    public Fraction(Fraction f){
        this.numerateur = f.numerateur;
        this.dénominateur = f.dénominateur;
    }

    public int getNumerateur(){
        return this.numerateur;
    }

    public int getDénominateur(){
        return this.dénominateur;
    }

    public void init(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrer le numerateur : ");
        this.numerateur = sc.nextInt();
        do{
            System.out.println("Entrer le dénominateur : ");
            this.dénominateur = sc.nextInt();
        }while(this.dénominateur == 0)
    }

    public String toString(){
        return this.numerateur + "/" + this.dénominateur;
    }

    public boolean equals(Fraction f){
        return this.numerateur == f.numerateur && this.dénominateur == f.dénominateur;
    }

    public Fraction inverse(){
        return new Fraction(this.dénominateur,this.numerateur);
    }

    public Fraction somme(Fraction f){
        return new Fraction(this.numerateur*f.dénominateur + this.dénominateur*f.numerateur,this.dénominateur*f.dénominateur);
    }

    public Fraction produit(Fraction f){
        return new Fraction(this.numerateur*f.numerateur,this.dénominateur*f.dénominateur);
    }
    

    public void afficher(){
        System.out.println(this.toString());
    }
}