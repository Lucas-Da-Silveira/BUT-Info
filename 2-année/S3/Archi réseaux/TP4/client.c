#include <arpa/inet.h>
#include <netinet/in.h>
#include <netinet/tcp.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <unistd.h>

int main(int argc, char *argv[])
{
    if (argc != 3) {
        fprintf(stderr, "Usage: %s adresse port\n", argv[0]);
        return 1;
    }

    const char *server = argv[1];
    const int port = atoi(argv[2]);

    /* création de la socket */
    int sock = socket(AF_INET, SOCK_STREAM, 0);
    if (sock < 0) {
        perror("socket");
        return 2;
    }

    /* encodage de l'adresse du serveur */
    struct sockaddr_in addr;
    addr.sin_family = AF_INET;
    addr.sin_port = htons(port);
    if (inet_aton(server, &addr.sin_addr) == 0) {
        fprintf(stderr, "adresse invalide: %s\n", server);
        return 2;
    }

    /* connexion */
    if (connect(sock, (struct sockaddr *)&addr, sizeof addr) != 0) {
        perror("connect");
        return 3;
    }

    /* envoi du message */
    const char message[4096];
    do{
        scanf("%s", message);
        write(sock, message, 4096);
    }while(1);
    

    /* fermeture de la connexion */
    close(sock);
}
