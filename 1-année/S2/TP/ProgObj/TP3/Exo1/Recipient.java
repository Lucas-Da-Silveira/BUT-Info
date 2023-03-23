class Recipient{
  private String nom;
  private int capacité;
  private int contenent;

  public Recipient(){
    this.nom = "NoName";
    this.capacité = 10;
    this.contenent = 0;
  }

  public Recipient(String nomR, int capa){
    this.nom = nomR;
    this.capacité = capa;
    this.contenent = 0;
  }

  public String getnom(){
    return this.nom;
  }

  public void setNom(String nom){
    this.nom = nom;
  }

  public int getcapacité(){
    return this.capacité;
  }

  public void setcapacité(int capa){
    this.capacité = capa;
  }

  public int getcontenent(){
    return this.contenent;
  }

  public void setcontenent(int contenent){
    this.contenent = contenent;
  }

  public boolean estVide(){
    return this.contenent == 0;
  }

  public boolean estPlein(){
    return this.contenent == this.capacité;
  }

  public void prélever(int quantité){
    if (this.contenent - quantité < 0){
      this.contenent = 0;
    }else{
      this.contenent -= quantité;
    }
  }

  public void verser(int quantité){
    if (this.contenent + quantité > this.capacité){
      this.contenent = this.capacité;
    }else{
      this.contenent += quantité;
    }
  }

  public void vider(){
    this.contenent = 0;
  }

  public void remplir(){
    this.contenent = this.capacité;
  }

  public void afficher(){
    System.out.println(this.nom + " contient " + this.contenent + " litres");
  }

}