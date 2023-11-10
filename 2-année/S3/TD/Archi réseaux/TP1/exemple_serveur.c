#include "client_serveur.h"
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#define DEBUG 0

#define VERIFIER(expr)                                                     \
    if (!(expr)) {                                                         \
        fprintf(stderr, "%s:%d: erreur: %s\n", __FILE__, __LINE__, #expr); \
        exit(2);                                                           \
    }

int main(int argc, char *argv[])
{
    if (argc != 2) {
        fprintf(stderr, "Usage: %s port\n", argv[0]);
        return 1;
    }
    int port = atoi(argv[1]);
    int a, b, c;
    int ret;

    int lsock = creer_serveur_tcp(port, DEBUG);
    VERIFIER(lsock != -1);
    int sock = attendre_client_tcp(lsock, DEBUG);
    VERIFIER(sock != -1);

    ret = read(sock, &a, sizeof(int));
    VERIFIER(ret == sizeof(int));
    ret = read(sock, &b, sizeof(int));
    VERIFIER(ret == sizeof(int));

    c = a + b;
    printf("# calcul de %d + %d -> %d\n", a, b, c);
    ret = write(sock, &c, sizeof(int));
    VERIFIER(ret == sizeof(int));

    ret = close(sock);
    VERIFIER(ret == 0);
    close(lsock);

    return 0;
}
