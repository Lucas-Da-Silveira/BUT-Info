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
    if (argc != 3) {
        fprintf(stderr, "Usage: %s serveur port\n", argv[0]);
        return 1;
    }
    char *serveur = argv[1];
    int port = atoi(argv[2]);
    int a, b, c;
    int ret;

    printf("? ");
    ret = scanf("%d%d", &a, &b);
    VERIFIER(ret == 2);
    int sock = creer_client_tcp(serveur, port, DEBUG);
    VERIFIER(sock != -1);

    ret = write(sock, &a, sizeof(int));
    VERIFIER(ret == sizeof(int));
    ret = write(sock, &b, sizeof(int));
    VERIFIER(ret == sizeof(int));

    ret = read(sock, &c, sizeof(int));
    VERIFIER(ret == sizeof(int));

    close(sock);
    printf("> %d\n", c);

    return 0;
}
