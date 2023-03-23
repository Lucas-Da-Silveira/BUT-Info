class Température{

    public static void echange(int[]tab, int i, int j){
        int temp = tab[i];
        tab[i] = tab[j];
        tab[j] = temp;
    }

    public static void trie(int[] tab, int low, int high) {
    int i = low, j = high;
    int pivot = tab[low + (high-low)/2];

    while (i <= j) {
      while (tab[i] < pivot)
        i++;
      while (tab[j] > pivot)
        j--;
      if (i <= j) {
              echange(tab, i, j);
        i++;
        j--;
      }
    }
    // Recursion
    if (low < j)
             trie(tab, low, j);
    if (i < high)
             trie(tab, i, high);
}

    public static void main(String[]args){
        int[]tab={7,8,9,7,4,3,2,2,5,7,7,1,-1,0,-2,
        -3,-3,2,0,-2,-5,-1,8,12,13,12,13,12};

        trie(tab,0,tab.length-1);
        for(int i=0;i<tab.length;i++){
            System.out.print(tab[i]+" ");
        }

    }
}



