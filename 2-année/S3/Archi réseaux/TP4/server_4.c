#include <arpa/inet.h>
#include <netinet/in.h>
#include <netinet/tcp.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>
#include <pthread.h> 

#define SERVER_BACKLOG 16
#define BUFSIZE 4096

void * connection_thread(void *vargp) {
    int sock = *((int*)vargp);
    if (sock < 0) {
        perror("accept");
    }
    /* réception du message */
    char message[BUFSIZE];
    int n;
    do{
        n = read(sock, message, BUFSIZE);
        if (n < 0) {
            perror("read");
        }
        if(n != 0) {
            printf("Message reçu : >>>%.*s<<<\n", n, message);
        }
    }while(n > 0);
    printf("%d\n", n);
    close(sock);
}

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

    pthread_t thread_id;
    while(1) {
        /* attente d'un client */
        struct sockaddr_in client_addr;
        int len = sizeof(client_addr);
        int sock = accept(lsock, (struct sockaddr *)&client_addr, &len);
        printf("IP client : %s\nPort client : %d\n", inet_ntoa(client_addr.sin_addr), ntohs(client_addr.sin_port));
        pthread_create(&thread_id, NULL, connection_thread, (void *)&sock);
        thread_id++;
    }

    close(lsock);
    
    return 0;

}
