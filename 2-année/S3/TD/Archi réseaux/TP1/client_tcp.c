/*
 * Client TCP utilisant :
 *
 *    int creer_client_tcp(char *adresse, int port, int debug)
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include "client_serveur.h"

#define DEBUG 0

int main(int argc, char *argv[])
{
    int socket, retour;
    char message[] = "Bonjour serveur";
    int taille_message = strlen(message) + 1;
    char message_serveur[256];
    char *serveur;
    int port;

    serveur = "127.0.0.1";
    port = 6666;
    switch (argc) {
    case 3:
        port = atoi(argv[2]);
        /* FALLTHROUGH */
    case 2:
        serveur = argv[1];
    }
    fprintf(stderr, "Connexion au serveur : %s au port : %d\n", serveur, port);
    socket = creer_client_tcp(serveur, port, DEBUG);
    if (socket < 0) {
        fprintf(stderr, "Echec de la connexion au serveur\n");
        exit(EXIT_FAILURE);
    }
    retour = write(socket, message, taille_message);
    if (retour != taille_message) {
        fprintf(stderr, "Echec de l'envoi du message au serveur\n");
        exit(EXIT_FAILURE);
    }

    retour = read(socket, message_serveur, sizeof message_serveur);
    if (retour <= 0) {
        fprintf(stderr,
                "Echec de la reception du message du serveur : %d\n",
                retour);
        exit(EXIT_FAILURE);
    }

    fprintf(stderr, "Message du serveur : %s\n", message_serveur);

    close(socket);
    exit(EXIT_SUCCESS);
}
