/*
 * Auteur : Lucas Da Silveira
 * Date : 23/12/2023
 */

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
    if (argc != 2) {
        fprintf(stderr, "Utilisation : %s port_serveur\n", argv[0]);
        return 1;
    }

    const int port = atoi(argv[1]);

    /* Ici on creer la socket */
    int server_socket = socket(AF_INET, SOCK_DGRAM, 0);
    if (server_socket < 0) {
        perror("socket");
        return 2;
    }

    /* On associe la socket a un port donné */
    struct sockaddr_in server_addr;
    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(port);
    server_addr.sin_addr.s_addr = htonl(INADDR_ANY);
    if (bind(server_socket, (struct sockaddr *) &server_addr, sizeof(server_addr)) != 0) {
        perror("bind");
        return 2;
    }

    do {
        /* On attend un message du client */
        struct sockaddr_in client_addr;
        socklen_t len = sizeof(client_addr);
        char message[MAX_MSG_SIZE];
        ssize_t received_bytes = recvfrom(server_socket, message, sizeof(message), 0, (struct sockaddr *) &client_addr,
                                          &len);
        if (received_bytes < 0) {
            perror("recvfrom");
            return 2;
        }

        /* On affiche les informations du clients */
        char client_domain[MAX_MSG_SIZE];
        if (getnameinfo((struct sockaddr *) &client_addr, sizeof(client_addr),
                        client_domain, sizeof(client_domain),
                        NULL, 0, NI_NAMEREQD) == 0) {
            printf("CLIENT: %s:%d (%s)\n", inet_ntoa(client_addr.sin_addr), ntohs(client_addr.sin_port), client_domain);
        } else {
            printf("CLIENT: %s:%d\n", inet_ntoa(client_addr.sin_addr), ntohs(client_addr.sin_port));
        }

        /* On creer le message de retour
         * En verifiant la taille du message
        */
        char response[MAX_MSG_SIZE];
        if (strlen(message) < sizeof(response)) {
            snprintf(response, sizeof(response), "Bonjour %s", message);
        } else {
            fprintf(stderr, "Erreur: Le message est trop long.\n");
        }

        /* On envoi le message au client */
        sendto(server_socket, response, strlen(response), 0, (struct sockaddr *) &client_addr, sizeof(client_addr));

    } while (1);

    close(server_socket);
}
