#include <arpa/inet.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <unistd.h>
#include <netdb.h>


/*#define MAX_MSG_SIZE 8
 * Ici message de 8 octets pour montrer l'erreur message
 * trop long donc impossible à renvoyer au client
 *
*/
#define MAX_MSG_SIZE 4096

int main(int argc, char *argv[]) {
    if (argc < 4) {
        fprintf(stderr, "Usage: %s adresse_serveur port_serveur mots...\n", argv[0]);
        return 1;
    }

    const char *server = argv[1];
    const int port = atoi(argv[2]);

    /* Ici on creer la socket */
    int sock = socket(AF_INET, SOCK_DGRAM, 0);
    if (sock < 0) {
        perror("socket");
        return 2;
    }

    /* encodage de l'adresse du serveur */
    struct sockaddr_in server_addr;
    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(port);

    struct in_addr addr;
    if (inet_aton(server, &addr) == 0) {
        struct addrinfo hints, *res;
        memset(&hints, 0, sizeof hints);
        hints.ai_family = AF_INET;
        hints.ai_socktype = SOCK_DGRAM;

        int status = getaddrinfo(server, NULL, &hints, &res);
        if (status != 0) {
            fprintf(stderr, "getaddrinfo: %s\n", gai_strerror(status));
            return 2;
        }

        struct sockaddr_in *ipv4 = (struct sockaddr_in *)res->ai_addr;
        addr = ipv4->sin_addr;

        freeaddrinfo(res);
    }
    server_addr.sin_addr = addr;

    /* construction du message */
    char message[MAX_MSG_SIZE];
    int offset = 0;
    for (int i = 3; i < argc && offset < MAX_MSG_SIZE; ++i) {
        int len = snprintf(message + offset, MAX_MSG_SIZE - offset, "%s ", argv[i]);
        if (len < 0) {
            perror("snprintf");
            return 2;
        }
        offset += len;
    }

    /* Vérification de la taille du message */
    if (offset >= MAX_MSG_SIZE) {
        fprintf(stderr, "Erreur: La taille du message dépasse la limite fixé (%d)\n", MAX_MSG_SIZE);
        return 3;
    }

    /* On envoi le message au serveur */
    sendto(sock, message, strlen(message), 0, (struct sockaddr *)&server_addr, sizeof(server_addr));

    /* réception de la réponse */
    char response[MAX_MSG_SIZE];
    ssize_t received_bytes = recvfrom(sock, response, sizeof(response), 0, NULL, NULL);
    if (received_bytes < 0) {
        perror("recvfrom");
        return 2;
    }

    /* On affiche la réponse du serveur */
    write(STDOUT_FILENO, response, received_bytes);

    /* Ferme le socket après avoir reçu le message du serveur */
    close(sock);

    return 0;
}
