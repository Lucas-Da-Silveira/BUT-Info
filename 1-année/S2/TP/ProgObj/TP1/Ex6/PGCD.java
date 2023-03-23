class PGCD{

    int pgcdRecursive(int a , int b){
        if (a == b){
            return a;
        }
        if (a > b){
            return pgcdRecursive(a-b, b);
        }
        return pgcdRecursive(a, b-a);
    }

    public static void main(String[] args){
        PGCD pgcd = new PGCD();
        System.out.println(pgcd.pgcdRecursive(12, 18));

    }
}