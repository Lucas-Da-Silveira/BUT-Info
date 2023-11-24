#include "client_serveur.h"
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#define DEBUG 0

#define VERIFIER(expr) \
if (!(expr)) { \
fprintf(stderr, "%s:%d: erreur: %s\n", __FILE__, __LINE__, #expr); \
exit(2); \
}

#define PORT 4242
#define SERVER_NAME "localhost"

/* Programme 1 : Connexion simple
int main(void) {

    unsigned int result, n;

    int sock = creer_client_tcp(SERVER_NAME, PORT, DEBUG);
    VERIFIER(sock != -1);

    printf("Entrer un nombre :\n");
    result = scanf("%u", &n);
    VERIFIER(result == 1);

    result = write(sock, &n, sizeof(unsigned int));
    VERIFIER(result == sizeof(unsigned int));

    close(sock);

    return 0;

}*/

/* Programme 2 : Calcul d'un tableau d'entiers et renvoi de la valeur de somme */
int main(void) {

    unsigned int result, size;
    int * numbers;
    int sum = 0;

    int sock = creer_client_tcp(SERVER_NAME, PORT, DEBUG);
    VERIFIER(sock != -1);

    printf("Entrer le nombre d'éléments à additionner :\n");
    result = scanf("%u", &size);
    VERIFIER(result == 1);

    numbers = malloc(size * sizeof(int));

    for(unsigned int i = 0; i < size; i++) {
        printf("Entrer le nombre n°%d : ", i+1);
        result = scanf("%d", numbers+i);
        VERIFIER(result == 1);
    }

    result = write(sock, &size, sizeof(unsigned int));
    VERIFIER(result == sizeof(unsigned int));

    result = write(sock, numbers, size * sizeof(int));
    VERIFIER(result == size * sizeof(int));

    result = read(sock, &sum, sizeof(sum));
    VERIFIER(result == sizeof(sum));

    printf("Somme calculée par le serveur : %d\n", sum);

    close(sock);

    return 0;

}