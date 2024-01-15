#include "client_serveur.h"
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <signal.h>

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

    int lsock = creer_serveur_tcp(PORT, DEBUG);
    VERIFIER(lsock != -1)

    int sock = attendre_client_tcp(lsock, DEBUG);
    VERIFIER(sock != -1)

    result = read(sock, &n, sizeof(unsigned int));
    VERIFIER(result == sizeof(unsigned int));

    printf("Reçu : %u, taille en octect : %lu\n", n, sizeof(n));

    result = close(sock);
    VERIFIER(result == 0);
    close(lsock);

    return 0;

}*/

/* Programme 2 : Calcul d'un tableau d'entiers et renvoi de la valeur de somme
int main(void) {

    unsigned int result, size;
    int * numbers;
    int sum = 0;

    int lsock = creer_serveur_tcp(PORT, DEBUG);
    VERIFIER(lsock != -1)

    int sock = attendre_client_tcp(lsock, DEBUG);
    VERIFIER(sock != -1)

    result = read(sock, &size, sizeof(unsigned int));
    VERIFIER(result == sizeof(unsigned int));
    printf("Taille du tableau reçu : %u\n", size);

    numbers = malloc(size * sizeof(int));
    result = read(sock, numbers, (size * sizeof(int)));
    VERIFIER(result == size * sizeof(int));
    for(unsigned int i = 0; i < size; i++) {
        sum += numbers[i];
        printf("%d", numbers[i]);
        if(i != size - 1) {
            printf(" + ");
        }
    }
    printf(" = %d\n", sum);

    result = write(sock, &sum, sizeof(sum));
    VERIFIER(result == sizeof(sum));

    result = close(sock);
    VERIFIER(result == 0);
    close(lsock);

    return 0;

}*/

/* Programme 3 : Server avec multiples processus fils */
int stop = 0;


void signal_handler(int sig_n) {
    if(sig_n == SIGUSR1) {
        stop = 1;
        printf("Signal reçu, terminaison du serveur.\n");
        kill(getpid(), SIGTERM);
    }
}

int main(void) {

    unsigned int result, size;
    int * numbers;
    int sum = 0;

    int lsock = creer_serveur_tcp(PORT, DEBUG);
    VERIFIER(lsock != -1)
    printf("PID du serveur : %d\n", getpid());
    signal(SIGUSR1, signal_handler);
    while(!stop) {
        int sock = attendre_client_tcp(lsock, DEBUG);
        VERIFIER(sock != -1)
        int pid = fork();
        if(pid == 0) {
            close(lsock);

            result = read(sock, &size, sizeof(unsigned int));
            VERIFIER(result == sizeof(unsigned int)); 
            printf("Taille du tableau reçue : %u\n", size);

            numbers = malloc(size * sizeof(int));
            result = read(sock, numbers, (size * sizeof(int)));
            VERIFIER(result == size * sizeof(int));
            for(unsigned int i = 0; i < size; i++) {
                sum += numbers[i];
                printf("%d", numbers[i]);
                if(i != size - 1) {
                    printf(" + ");
                }
            }
            printf(" = %d\n", sum);

            result = write(sock, &sum, sizeof(sum));
            VERIFIER(result == sizeof(sum));

            exit(0);
        }

        result = close(sock);
        VERIFIER(result == 0);

        int status;
        wait(&status);
        if (WIFEXITED(status)) {
            printf("Processus %d : mon fils %d s'est terminé", getpid(), pid);
            if (WEXITSTATUS(status) == 0) {
                printf(" normalement.\n");
            } else {
                printf(" anormalement.\n");
            }
        }
    }

    close(lsock);

    return 0;

}