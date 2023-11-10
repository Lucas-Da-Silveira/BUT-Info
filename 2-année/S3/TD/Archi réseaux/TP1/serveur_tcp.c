/*
 * Serveur TCP utilisant les fonctions :
 *
 *    int creer_serveur_tcp(int port, int max_client, int debug)
 *    int attendre_client_tcp(int socket_serveur, int debug)
 */

#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include "client_serveur.h"

#define DEBUG 0

void fin_fils(int n_signal)
{
    if (n_signal != SIGCHLD)
        fprintf(stderr,
                "Serveur: etonnant la fonction de traitement de SIGCHILD ets appelee avec un signal different de SIGCHILD\n");
    wait(NULL);
    fprintf(stderr, "Serveur: code de retour du fils purge\n");
}

int main(void)
{
    int socket_serveur, socket_client, fils, retour;
    char message[256];
    char salut[] = "Bonjour client";
    int taille_salut = strlen(salut) + 1;

    /* Capture du signal de fin de fils */
    struct sigaction act = {
        .sa_handler = fin_fils,
        .sa_flags = SA_RESTART
    };
    sigemptyset(&act.sa_mask);
    sigaction(SIGCHLD, &act, NULL);

    socket_serveur = creer_serveur_tcp(6666, DEBUG);
    if (socket_serveur < 0) {
        fprintf(stderr, "Echec de la creation du serveur\n");
        exit(EXIT_FAILURE);
    }

    while (1) {
        socket_client = attendre_client_tcp(socket_serveur, DEBUG);

        if (socket_client < 0) {
            fprintf(stderr, "Echec de l'attente du client\n");
            close(socket_serveur);
            exit(EXIT_FAILURE);
        }

        if ((fils = fork()) < 0) {
            fprintf(stderr, "Echec du fork\n");
            close(socket_serveur);
            close(socket_client);
            exit(EXIT_FAILURE);
        }

        if (fils == 0) {
            close(socket_serveur);
            retour = read(socket_client, message, sizeof message);
            if (retour <= 0) {
                fprintf(stderr, "Le serveur n'a pas recu le message\n");
                close(socket_client);
                exit(EXIT_FAILURE);
            }

            fprintf(stdout, "Message recu : %s\n", message);

            retour = write(socket_client, salut, taille_salut);
            if (retour != taille_salut) {
                fprintf(stderr, "Echec de l'envoi du message au client\n");
                close(socket_client);
                exit(EXIT_FAILURE);
            }
            close(socket_client);
            exit(EXIT_SUCCESS);
        }

        fprintf(stderr, "Serveur : fermeture de la socket client\n");
        close(socket_client);
    }
}
