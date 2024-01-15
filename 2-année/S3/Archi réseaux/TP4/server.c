#include <arpa/inet.h>
#include <netinet/in.h>
#include <netinet/tcp.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <unistd.h>

#define SERVER_BACKLOG 16
#define BUFSIZE 4096

int main(int argc, char *argv[])
{
    if (argc != 2) {
        fprintf(stderr, "Usage: %s port\n", argv[0]);
        return 1;
    }

    const int port = atoi(argv[1]);

     /* création de la socket */
    int lsock = socket(AF_INET, SOCK_STREAM, 0);
    if (lsock < 0) {
        perror("socket");
        return 2;
    }

    /* association de la socket au port donné */
    struct sockaddr_in server_addr;
    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(port);
    server_addr.sin_addr.s_addr = htonl(INADDR_ANY);
    if (bind(lsock, (struct sockaddr *)&server_addr, sizeof server_addr) != 0) {
        perror("bind");
        return 2;
    }

    /* mise en écoute de la socket */
    if (listen(lsock, SERVER_BACKLOG) != 0) {
        perror("listen");
        return 2;
    }

    /* attente d'un client */
    struct sockaddr_in client_addr;
    int len = sizeof(client_addr);
    int sock = accept(lsock, (struct sockaddr *)&client_addr, &len);
    if (sock < 0) {
        perror("accept");
        return 2;
    }

    printf("IP client : %s\nPort client : %d\n", inet_ntoa(client_addr.sin_addr), ntohs(client_addr.sin_port));
    /* réception du message */
    char message[BUFSIZE];
    do{
        int n = read(sock, message, BUFSIZE);
        if (n < 0) {
            perror("read");
            return 3;
        }
        if(n != 0) {
            printf("Message reçu : >>>%.*s<<<\n", n, message);
        }
    }while(1);

    close(sock);
    close(lsock);
}
