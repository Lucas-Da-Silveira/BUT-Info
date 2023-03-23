public class Personne {
    private java.lang.String nom;
    private int age;

    public void Personne(){
    }

    public void Personne(Personne p){
        this.nom = p.nom;
        this.age = p.age;
    }

    public void Personne(java.lang.String n, int a){
        this.nom = n;
        this.age = a;
    }
    
    public boolean equals(Object o){
        if (o instanceof Personne){
            Personne p = (Personne) o;
            return (this.nom.equals(p.nom) && this.age == p.age);
        } 
        else {
            return false;
        }
    }

    public String getNom(){
        return this.nom;
    }

    public void setNom(String unNom){
        this.nom = unNom;
    }

    public int getAge(){
        return this.age;
    }

    public void setAge(int unAge){
        this.age = unAge;
    }

    public void init(){
        this.nom = "Inconnu";
        this.age = 0;
    }

    public String toString(){
        return "Nom : " + this.nom + " Age : " + this.age;
    }
}
