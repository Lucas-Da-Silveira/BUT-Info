#include <stdio.h>

int main(void){
    int n = 10;
    int somme = 0;
    int i = 1;
    while(i <= n){
        somme += i;
        i++;
    }
    printf("La somme des entiers de 1 à %d est %d\n", n, somme);
    return 0;
}