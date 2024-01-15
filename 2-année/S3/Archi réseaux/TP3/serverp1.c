#include "client_serveur.h"
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

#define DEBUG 0

#define VERIFIER(expr) \
if (!(expr)) { \
fprintf(stderr, "%s:%d: erreur: %s\n", __FILE__, __LINE__, #expr); \
exit(2); \
}

#define PORT 7777
#define SERVER_NAME "localhost"
#define MAX_SIZE 16

int array_without_zeros(int * tab, int size) {
    int count = 0;
    for(int i = 0; i < size; i++) {
        if(tab[i] != 0) {
            tab[count] = tab[i];
            count++;
        }
    }
    return count;
}


int main(void) {

    unsigned int size;
    int result;
    int tab[MAX_SIZE];

    int lsock = creer_serveur_tcp(PORT, DEBUG);
    VERIFIER(lsock != -1)

    while(1) {
        int sock = attendre_client_tcp(lsock, DEBUG);
        VERIFIER(sock != -1)
        int pid = fork();
        if(pid == 0) {
            close(lsock);

            result = read(sock, &size, sizeof(unsigned int));
            VERIFIER(result == sizeof(unsigned int)); 
            printf("Taille du tableau reçue : %u\n", size);

            if(size <= MAX_SIZE) {
                result = read(sock, tab, (size * sizeof(int)));
                VERIFIER(result == (int)(size * sizeof(int)));

                size = array_without_zeros(tab, size);

                result = write(sock, &size, sizeof(unsigned int));
                VERIFIER(result == sizeof(unsigned int)); 

                result = write(sock, tab, (size * sizeof(int)));
                VERIFIER(result == (int)(size * sizeof(int)));
            }

            exit(0);
        }

        close(sock);
        
        int status;
        wait(&status);
        if (WIFEXITED(status)) {
            printf("Serveur : connexion avec client (processus %d) s'est terminé", pid);
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