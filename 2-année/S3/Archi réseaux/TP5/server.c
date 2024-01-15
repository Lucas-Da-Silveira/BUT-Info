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

    do {
        /* attente d'un client */
        struct sockaddr_in client_addr;
        int len = sizeof(client_addr);
        int sock = accept(lsock, (struct sockaddr *)&client_addr, &len);
        if (sock < 0) {
            perror("accept");
            return 2;
        }

        int n = 0;
        char message[100000];
        
        printf("IP client : %s\nPort client : %d\n", inet_ntoa(client_addr.sin_addr), ntohs(client_addr.sin_port));
        n = recvfrom(sock, message, sizeof(message), 0, (struct sockaddr *)&client_addr, &len);
        printf("%s\n", message);
        printf("%d\n", n);
        char message_to_send[64];
        snprintf(message_to_send, 64, "Lu un message de %d octets", n);
        //sleep(1);
        sendto(sock, message_to_send, sizeof(message_to_send), 0, (struct sockaddr *)&client_addr, len);

        close(sock);
    }while(1);

    close(lsock);
}
