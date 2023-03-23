class Personne{

        public String nom;
        public String prenom;
        public int jourNaiss;
        public int moisNaiss;
        public int anneeNaiss;

        public void setPrenom( String n_prenom){
            this.prenom = n_prenom;
        }

        public void setNom( String n_nom){
            this.nom = n_nom;
        }

        public void setDateNaiss(int j,int m, int a){
            this.jourNaiss = j;
            this.moisNaiss = m;
            this.anneeNaiss = a;
        }

        public int ageEn2014(){
            return 2014 - anneeNaiss;
        }

        public String toString(){
            return prenom + ", " + nom + " (" + jourNaiss + "/" + moisNaiss + "/" + anneeNaiss + ")";

        }
}
