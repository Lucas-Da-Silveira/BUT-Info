#include "stdio.h"

int copie(FILE*entree, FILE*sortie){
    int c;
    while((c = fgetc(entree)) != EOF){
        fputc(c, sortie);
    }
    return 0;
}



int main(){
    FILE *fptr1, *fptr2;
    char filename[100];

    printf("Enter the filename to open for reading \n");
    scanf("%s", filename);

    fptr1 = fopen(filename, "r");
    if (fptr1 == NULL)
    {
        printf("Cannot open file %s \n", filename);
        return 0;
    }

    printf("Enter the filename to open for writing \n");
    scanf("%s", filename);

    fptr2 = fopen(filename, "w");
    if (fptr2 == NULL)
    {
        printf("Cannot open file %s \n", filename);
        return 0;
    }

    copie(fptr1, fptr2);
    printf("\nContents copied to %s", filename);

    fclose(fptr1);
    fclose(fptr2);
    return 0;
}