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

#define PORT 4021
#define SERVER_NAME "localhost"


int main(void) {

    unsigned int result, size;
    char * file_name;
    char * content;

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
            printf("Taille de la chaîne reçue : %u\n", size);

            file_name = malloc(size * sizeof(char));
            result = read(sock, file_name, (size * sizeof(char)));
            VERIFIER(result == size * sizeof(char));

            printf("%s\n", file_name);

            content = malloc(4096 * sizeof(char));
            do{
                result = read(sock, content, (4096 * sizeof(char)));
                printf("%s", content);
            }while (result != 0);
            
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