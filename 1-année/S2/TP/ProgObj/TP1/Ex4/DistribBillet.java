class DistribBillet{

    public static void main(String[] args){

        int limCinq = 100/5;
        int limDix = 100/10;
        int limVingt = 100/20;

        for (int i = 0; i < limCinq; i++){
            for (int j = 0; j < limDix; j++){
                for (int k = 0; k < limVingt; k++){
                    if (i*5 + j*10 + k*20 == 100){
                        System.out.println("100 = "+i+" *5 + "+j+" *10 + "+k+" *20 ");
                    }
                }
            }
        }
    }
}