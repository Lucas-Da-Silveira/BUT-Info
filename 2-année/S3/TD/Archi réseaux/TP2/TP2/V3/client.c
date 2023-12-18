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

    int result, test, new_port;
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

    printf("Entrer le port de connexion pour l'envoi du contenu : ");
    scanf("%d", &new_port);
    result = write(sock, &new_port, sizeof(int));
    VERIFIER(result == sizeof(int));

    result = read(sock, &test, sizeof(int));
    VERIFIER(result == sizeof(int));

    int sock2 =  sock2 = creer_client_tcp(SERVER_NAME, new_port, DEBUG);
    
    snprintf(output, 2, "%d", sock2);
    command = malloc(sizeof(output) + (size * sizeof(char)) + 8 * sizeof(char));

    strcpy(command, "cat ");
    strcat(command, file_name);
    dup2(sock2, 1);
    execl("/bin/sh", "/bin/sh", "-c", command, NULL);

    close(sock2);
    close(sock);
    
    return 0;

}