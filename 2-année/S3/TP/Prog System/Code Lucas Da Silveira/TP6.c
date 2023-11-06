#include <stdio.h>
#include <unistd.h>
#include <signal.h>
#include <stdlib.h>

//Prog 1
//V1
/*void signal_handler(int signo) {
    printf("Signal reçu : %d\n", signo);
}

int main() {
    for (int signo = 1; signo < _NSIG; signo++) {
        signal(signo, signal_handler);
    }

    while(1) {
    }

    return 0;
}*/

//V2
/*void signal_handler(int signo) {
    printf("Signal reçu : %d\n", signo);
    signal(signo, SIG_IGN);
}

int main() {
    for (int signo = 1; signo < _NSIG; signo++) {
        signal(signo, signal_handler);
    }

    while (1) {
    }

    return 0;
}*/

//V3
/*void signal_handler(int signo, siginfo_t *info, void *context) {
    printf("Signal reçu : %d\n", signo);
}

int main() {
    struct sigaction sa;
    sa.sa_flags = SA_SIGINFO;
    sa.sa_sigaction = signal_handler;

    for (int signo = 1; signo < _NSIG; signo++) {
        sigaction(signo, &sa, NULL);
    }

    while (1) {
    }

    return 0;
}*/

//V4
/*void signal_handler(int signo, siginfo_t *info, void *context) {
    printf("Signal reçu : %d\n", signo);
    struct sigaction sa;
    sa.sa_flags = SA_SIGINFO | SA_RESETHAND;
    sa.sa_sigaction = signal_handler;
    sigaction(signo, &sa, NULL);
}*/

/*int main() {
    struct sigaction sa;
    sa.sa_flags = SA_SIGINFO | SA_RESETHAND;
    sa.sa_sigaction = signal_handler;

    for (int signo = 1; signo < _NSIG; signo++) {
        sigaction(signo, &sa, NULL);
    }

    while (1) {
    }

    return 0;
}*/

//Prog2

int messages = 10;

void signal_handler(int signo) {
    if (messages > 0) {
        if (signo == SIGUSR1) {
            printf("IUT ");
            fflush(stdout);
            kill(getppid(), SIGUSR1);
        } else if (signo == SIGUSR2) {
            printf("Belfort ");
            fflush(stdout);
            kill(getppid(), SIGUSR2);
        } else if (signo == SI_USER) {
            printf("Montbéliard\n");
            fflush(stdout);
            kill(getppid(), SI_USER);
            messages--;
        }
    }
}

int main() {
    pid_t child1, child2, child3;
    
    child1 = fork();
    if (child1 == 0) {
        signal(SIGUSR1, signal_handler);
        while (1) {
            pause();
        }
    } else {
        child2 = fork();
        if (child2 == 0) {
            signal(SIGUSR2, signal_handler);
            while (1) {
                pause();
            }
        } else {
            child3 = fork();
            if (child3 == 0) {
                signal(SI_USER, signal_handler);
                while (1) {
                    pause();
                }
            } else {
                sleep(1); //
                kill(child3, SIGUSR1);
                while (messages > 0) {
                    pause();
                }
                kill(child1, SIGTERM);
                kill(child2, SIGTERM);
                kill(child3, SIGTERM);
                wait(NULL);
                wait(NULL);
                wait(NULL);
            }
        }
    }

    return 0;
}