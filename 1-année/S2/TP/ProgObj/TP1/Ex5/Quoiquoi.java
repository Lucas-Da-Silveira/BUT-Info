class Quoiquoi{

//calcul une puissance
    int quoiquoi (int i, int j){ 
        if (j == 0) return 1;
    return i*quoiquoi(i,j-1);
    }

    int quoiquoiItératif(int a, int b){
        int res = 1;
        for (int i = 0; i < b; i++){
            res *= a;
        }
        return res;
    }


//calcul un carré
    int quoiquoi (int n){ 
        if (n == 0) return 0;
    return quoiquoi(n-1)+2*n-1;
    }

    


    public static void main(String[]args){
        Quoiquoi q = new Quoiquoi();
        System.out.println(q.quoiquoi(2,3));
        System.out.println(q.quoiquoiItératif(2,3));
        System.out.println(q.quoiquoi(3));
        
    }
}