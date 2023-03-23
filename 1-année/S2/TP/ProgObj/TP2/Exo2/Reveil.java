class Reveil{

    public int heureCourante;
    public int minuteCourante;
    public int secondeCourante;
    public int heureAlarme;
    public int minuteAlarme;
    public int secondeAlarme;
    public boolean alarmeActive;

    public void setHorraireCourant(int heure, int minute, int seconde){
        this.heureCourante = heure;
        this.minuteCourante = minute;
        this.secondeCourante = seconde;
    }

    public void setHorraireAlarme(int heure, int minute, int seconde){
        this.heureAlarme = heure;
        this.minuteAlarme = minute;
        this.secondeAlarme = seconde;
    }

    public void allumerAlarme(){
        this.alarmeActive = true;
    }

    public void eteindreAlarme(){
        this.alarmeActive = false;
    }

    public void controlerAlarme(){
        if (this.alarmeActive == true){
            if (this.heureCourante == this.heureAlarme && this.minuteCourante == this.minuteAlarme && this.secondeCourante == this.secondeAlarme){
                System.out.println("BIP BIP BIP");
            }
        }else{
            System.out.println("L'alarme est éteinte");
        }
    }

    public void comparerA(Reveil r){
        if (this.heureCourante == r.heureCourante && this.minuteCourante == r.minuteCourante && this.secondeCourante == r.secondeCourante){
            System.out.println("Les horraires sont identiques");
        }else{
            System.out.println("Les horraires sont différents");
        }
    }

    public void differenceAvec(Reveil r){
        int heureDiff = (this.heureCourante*3600) - (r.heureCourante*3600);
        int minuteDiff = (this.minuteCourante*60) - (r.minuteCourante*60);
        int secondeDiff = this.secondeCourante - r.secondeCourante;
        int totalDiff = heureDiff + minuteDiff + secondeDiff;
        System.out.println("La différence entre les deux horraires est de " + totalDiff + " secondes");
    }

    public void incrementer(){
        this.secondeCourante++;
        if (this.secondeCourante == 60){
            this.secondeCourante = 0;
            this.minuteCourante++;
            if (this.minuteCourante == 60){
                this.minuteCourante = 0;
                this.heureCourante++;
                if (this.heureCourante == 24){
                    this.heureCourante = 0;
                }
            }
        }
    }

    public String toString(){
        return "Horraire courant: " + this.heureCourante + "h " + this.minuteCourante + "min " + this.secondeCourante + "s";
    }
       
    
}