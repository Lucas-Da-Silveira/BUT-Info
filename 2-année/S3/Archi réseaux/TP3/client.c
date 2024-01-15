#include "client_serveur.h"
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

#define DEBUG 0

#define VERIFIER(expr) \
if (!(expr)) { \
fprintf(stderr, "%s:%d: erreur: %s\n", __FILE__, __LINE__, #expr); \
exit(2); \
}

#define PORT 7777
#define SERVER_NAME "localhost"

int main() {

    int result;
    unsigned int size;

    int sock = creer_client_tcp(SERVER_NAME, PORT, DEBUG);
    VERIFIER(sock != -1);

    int tab[10] = {1, 0, 0, 1, 1, 0, 1, 0, 0, 1};
    size = 10;
    result = write(sock, &size, sizeof(unsigned int));
    VERIFIER(result == sizeof(unsigned int)); 

    result = write(sock, tab, (size * sizeof(int)));
    VERIFIER(result == (int)(size * sizeof(int)));

    result = read(sock, &size, sizeof(unsigned int));
    VERIFIER(result == sizeof(unsigned int)); 

    int * tab_bis = malloc(size * sizeof(int));

    result = read(sock, tab_bis, size * sizeof(int));
    VERIFIER(result == (int)(size * sizeof(int))); 

    for(int i = 0; i < (int)size; i++) {
        printf("%d ", tab_bis[i]);
    }
    printf("\n");

    close(sock);

    sock = creer_client_tcp(SERVER_NAME, PORT, DEBUG);
    VERIFIER(sock != -1);

    int tab2[256] = {1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 2, 3, 4, 0, 3, 0, 1, 2, 0, 0, 2, 3, 0, 0, 0, 1};
    size = 256;
    result = write(sock, &size, sizeof(unsigned int));
    VERIFIER(result == sizeof(unsigned int)); 

    result = write(sock, tab2, (size * sizeof(int)));
    VERIFIER(result == (int)(size * sizeof(int)));

    result = read(sock, &size, sizeof(unsigned int));
    VERIFIER(result == sizeof(unsigned int)); 

    int * tab2_bis = malloc(size * sizeof(int));

    result = read(sock, tab2_bis, size * sizeof(int));
    VERIFIER(result == (int)(size * sizeof(int))); 

    for(int i = 0; i < (int)size; i++) {
        printf("%d ", tab2_bis[i]);
    }
    printf("\n");

    close(sock);

    return 0;

}