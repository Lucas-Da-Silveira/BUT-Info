class Voiture{

    private int vitesse;


    public int voiture(){
        this.vitesse = 0;
        return this.vitesse;
    }

    public int voiture(int vitesseInitial){
        this.vitesse = vitesseInitial;
        return this.vitesse;
    }

    public void accélérer(){
        this.vitesse += 1;
    }

    public void accélérer(int nbVitesse){
        this.vitesse += nbVitesse;
    }

    public void ralentir(){
        this.vitesse -= 1;
    }

    public int getVitesse(){
        return this.vitesse;
    }


}