#include <stdio.h>

char *write();

int main(){
    char x[50] = "Prout";
    char buff[60];
    printf("%s, Bonjour\n", write(x, buff));
    return 0;
}  