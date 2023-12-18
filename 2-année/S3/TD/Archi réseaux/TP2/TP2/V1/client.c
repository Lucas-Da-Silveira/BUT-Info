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

#define PORT 4021
#define SERVER_NAME "localhost"
#define TAILLE_TAMPON 4096

int main() {

    int result;
    unsigned int size;
    char * file_name = malloc(TAILLE_TAMPON * sizeof(char));
    char output[2];
    char * command;

    int sock = creer_client_tcp(SERVER_NAME, PORT, DEBUG);
    VERIFIER(sock != -1);

    printf("Entrer le nom du fichier à envoyer : ");
    result = scanf("%s", file_name);
    VERIFIER(result == 1);

    size = strlen(file_name) + 1;

    result = write(sock, &size, sizeof(unsigned int));
    VERIFIER(result == sizeof(unsigned int)); 

    result = write(sock, file_name, (size * sizeof(char)));
    VERIFIER(result == (int)(size * sizeof(char)));

    snprintf(output, 2, "%d", sock);
    command = malloc(sizeof(output) + (size * sizeof(char)) + 8 * sizeof(char));

    strcpy(command, "cat ");
    strcat(command, file_name);
    strcat(command, " 1>&");
    strcat(command, output);
    dup2(sock, 1);
    execl("/bin/sh", "/bin/sh", "-c", command, NULL);

    close(sock);

    return 0;

}