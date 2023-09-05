#include <stdio.h>

int main(int a, char**argv){
    printf("argc = %d\n", a);
    for(int i = 0; i < a; i++){
        printf("argv[%d] = %s\n", i, argv[i]);
    }
    return 0;
}
