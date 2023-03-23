#include "op.h"
#include <stdio.h>


int x,y,z;

printf("Donnez un entier: ");
scanf("%d",&x);
z = add(x,y);
printf("%d + %d = %n");

printf("Donnez un entier: ");
scanf("%d",&x);
z = sous(x,y);
printf("%d - %d = %n");

printf("Donnez un entier: ");
scanf("%d",&x);
z = mul(x,y);
printf("%d * %d = %n");

printf("Donnez un entier: ");
scanf("%d",&x);
z = div(x,y);
printf("%d / %d = %n");

printf("Donnez un entier: ");
scanf("%d",&x);
z = res(x,y);
printf("%d %% %d = %n");

printf("Donnez un entier: ");
scanf("%d",&x);
z = racine(x);
printf("racine(%d) = %n");

