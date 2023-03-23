class Tab2d{
    
    public static int identité (int taille){

        for(int i = 0; i < taille; i++){
            for(int j = 0; j < taille; j++){
                if(i == j){
                    System.out.print("1 ");
                }else{
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
        return 0;
    }

    public static int remiseAZero(int taille){
        int[][] matrice = new int[taille][taille];
        for(int i = 0; i < taille; i++){
            for(int j = 0; j < taille; j++){
                matrice[i][j] = 0;
                System.out.print("0 ");
            }
            System.out.println();
        }
        return 0;
    }


    public static void main(String[]args){
        identité(5);
        System.out.println();
        remiseAZero(5);
    }
}