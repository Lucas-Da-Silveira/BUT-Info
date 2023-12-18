import java.util.Random;

abstract class Equide{

    int force;
    int endurance;
    int age;
    int esperenceDeVie;
    static int M=0;
    static int F=1;
    static Random loto;

    public Equide(){
       loto = new Random();
       if(getGenre() == M){
           force = 6;
           endurance = 4;
       }else {
           force = 4;
           endurance = 6;
       }
       force +=2 - loto.nextInt(5);
       endurance +=2 - loto.nextInt(5);
       esperenceDeVie = 20;
       age = 0;
    }

    public int getGenre(){
        return F;
    }

    public int tracter(){
        return force*30;
    }

    public int courir(){
        return 15*endurance;
    }
}

abstract class Bardots extends Equide{
    Bardots(){
        super();
        this.force -=1;
    }
}

abstract class Chevaux extends Equide{
    Chevaux() {
        super();
        this.force += 1;
        this.endurance -= 1;
    }

    abstract public Equide rencontre(Equide e);

    public int courir(){
        return 10*endurance;
    }
}

class Anes extends Equide{
    Anes(){
        super();
        this.endurance +=1;
    }
    public int tracter(){
        return 50*force;
    }
}

class Mules extends Equide{
    Mules(){
        super();
        this.endurance +=1;
        this.esperenceDeVie +=5;
    }

    public Equide rencontre (Equide e){
        return null;
    }
}

class Bardot extends Bardots{
    Bardot(){
        super();
    }
    public int getGenre(){
        return M;
    }
}

class Bardine extends Bardots{
    Bardine(){
        super();
    }

    public int courir(){
        return 12*endurance;
    }
}

class Etalon extends Chevaux{
    Etalon(){
        super();
    }
    public int getGenre(){
        return M;
    }

    public Equide rencontre(Equide e){
        Equide bebe = null;
        if((this.age<3) || (e.age<3) || (e.age>18)) return null;
        if(e instanceof Jument){
            int sexe = loto.nextInt(2);
            if(sexe == M){
                bebe = new Etalon();
            }else{
                bebe = new Jument();
            }
        }else if(e instanceof Anesse){
            int sexe = loto.nextInt(2);
            if(sexe == M){
                bebe = new Bardot();
            }else{
                bebe = new Bardine();
            }
        }
        return bebe;
    }

}

class Jument extends Chevaux{
    Jument(){
        super();
    }

    public Equide rencontre(Equide e){
        Equide bebe = null;
        if((this.age<3) || (e.age<3) || (e.age>18)) return null;
        if(e instanceof Etalon){
            int sexe = loto.nextInt(2);
            if(sexe == M){
                bebe = new Etalon();
            }else{
                bebe = new Jument();
            }
        }else if(e instanceof Ane){
            int sexe = loto.nextInt(2);
            if(sexe == M){
                bebe = new Mulet();
            }else{
                bebe = new Mule();
            }
        }
        return bebe;
    }
}

class Ane extends Anes{
    Ane(){
        super();
        this.endurance +=1;
        this.esperenceDeVie +=5;
    }
    public int getGenre(){
        return M;
    }

    public Equide rencontre(Equide e){
        Equide bebe = null;
        if((this.age<3) || (e.age<3) || (e.age>18)) return null;
        if(e instanceof Jument){
            int sexe = loto.nextInt(2);
            if(sexe == M){
                bebe = new Mulet();
            }else{
                bebe = new Mule();
            }
        }else if(e instanceof Anesse){
            int sexe = loto.nextInt(2);
            if(sexe == M){
                bebe = new Ane();
            }else{
                bebe = new Anesse();
            }
        }
        return bebe;
    }

}

class Anesse extends Anes{
    Anesse(){
        super();
    }

    public Equide rencontre(Equide e){
        Equide bebe = null;
        if((this.age<3) || (e.age<3) || (e.age>18)) return null;
        if(e instanceof Etalon){
            int sexe = loto.nextInt(2);
            if(sexe == M){
                bebe = new Bardot();
            }else{
                bebe = new Bardine();
            }
        }else if(e instanceof Ane){
            int sexe = loto.nextInt(2);
            if(sexe == M){
                bebe = new Ane();
            }else{
                bebe = new Anesse();
            }
        }
        return bebe;
    }

}

class Mulet extends Mules{
    Mulet(){
        super();
    }
    public int getGenre(){
        return M;
    }

    public Equide renvcontre(Equide e){
        return null;
    }

}

class Mule extends Mules{
    Mule(){
        super();
    }
    public Equide renvcontre(Equide e){
        return null;
    }

}