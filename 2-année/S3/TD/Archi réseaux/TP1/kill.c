#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <signal.h>

int main(int argc, char * argv[]) {
    if(argc >= 2) {
        kill(atoi(argv[1]), SIGUSR1);
    }

    return 0;

}