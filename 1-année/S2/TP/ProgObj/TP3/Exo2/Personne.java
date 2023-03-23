class Personne{

        public String nom;
        public String prenom;
        public int jourNaiss;
        public int moisNaiss;
        public int anneeNaiss;
        public char sexe;
        public boolean estMarie;
        public Personne conjoint;

        public Personne(String n_prenom, String n_nom, int j, int m, int a, char s){
            this.prenom = n_prenom;
            this.nom = n_nom;
            this.jourNaiss = j;
            this.moisNaiss = m;
            this.anneeNaiss = a;
            this.sexe = s;
            this.estMarie = false;
            this.conjoint = null;
        }

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

        public void setSexe(char s){
            this.sexe = s;
        }

        public void setEstMarie(boolean estMarie){
            this.estMarie = estMarie;
        }

        public void setConjoint(Personne conjoint){
            if(!this.estMarie){
                this.conjoint = conjoint;
                this.estMarie = true;
                conjoint.setEstMarie(true);
                conjoint.setConjoint(this);
            }
        }

        public int ageEn2014(){
            return 2014 - anneeNaiss;
        }

        public String toString(){
            String mariage = "";
            if(this.estMarie){
                mariage = ", marié(e) avec "+ conjoint.prenom;
            }
            return prenom + ", " + nom + " (" + jourNaiss + "/" + moisNaiss + "/" + anneeNaiss + ")" + sexe + mariage;

        }
}
