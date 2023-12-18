#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

void affiche_car(const char *prefixe){
    printf("%s Processus numéro : %d. Numéro du père : %d\n", prefixe, getpid(), getppid());
}

//Prog 1

/*int main(){
    int i;
    for (i = 0; i < 100; i++) {
        int pid = fork();
        if (pid == 0) {
            affiche_car("Fils:");
            exit(i);
        } else if (pid < 0) {
            fprintf(stderr, "La création du processus fils a échoué.\n");
            return 1;
        }
    }
    int status;
    int pid;
    while((pid = wait(&status)) > 0){
        if(WIFEXITED(status)){
            printf("Processus %d : mon fils %d s'est terminé %s.\n", getpid(), pid, WEXITSTATUS(status) ? "anormalement" : "normalement");
        }
    }


    return 0;
}*/

//Prog 2

/*
int main() {
    int i;
    for (i = 0; i < 100; i++) {
        pid_t pid = fork();
        if (pid == 0) {
            printf("Fils %d - PID : %d\n", i + 1, getpid());
            exit(0);
        } else if (pid < 0) {
            fprintf(stderr, "La création du processus fils a échoué.\n");
            return 1;
        }
    }

    for (i = 0; i < 100; i++) {
        wait(NULL);
    }

    return 0;
}*/

//Prog 3
/*
void creer_processus_hierarchie(int niveau, int max_niveau) {
    if (niveau >= max_niveau) {
        return;
    }

    int pid = fork();
    if (pid == 0) {
        affiche_car("Fils:");
        creer_processus_hierarchie(niveau + 1, max_niveau);
        exit(0);
    } else if (pid < 0) {
        fprintf(stderr, "La création du processus fils a échoué.\n");
        exit(1);
    } else {
        int status;
        wait(&status);
    }
}

int main() {
    creer_processus_hierarchie(0, 100);
    return 0;
}*/

// Prog 4

/*int main() {
    int i;
    int num_processes = 100;

    for (i = 0; i < num_processes; i++) {
        int pid = fork();
        if (pid == 0) { // Code exécuté par le fils
            affiche_car("Fils:");
            int petit_fils_pid = fork();
            if (petit_fils_pid == 0) {
                affiche_car("Petit-fils:");
                exit(0);
            } else if (petit_fils_pid < 0) {
                fprintf(stderr, "La création du processus petit-fils a échoué.\n");
                exit(1);
            }
            wait(NULL);
            exit(0);
        } else if (pid < 0) {
            fprintf(stderr, "La création du processus fils a échoué.\n");
            exit(1);
        }
    }

    int status;
    for (i = 0; i < num_processes; i++) {
        wait(&status);
    }

    printf("Tous les fils et petits-fils sont terminés. Processus initial se termine.\n");

    return 0;
}*/

//Prog 5


int main(void) {
    for (int i = 0; i < 100; i++) {
        int pid = fork();

        printf("\n");

        if (pid < 0) {
            fprintf(stderr, "La création du processus fils a échoué.\n");
            exit(1);
        }
        else if (pid == 0) {
            affiche_car("Fils");

            int petit_fils_pid = fork();

            if (petit_fils_pid < 0) {
                fprintf(stderr, "La création du processus petit-fils a échoué.\n");
                exit(1);
            }
            else if (petit_fils_pid == 0) {
                affiche_car("Petit-fils");
                exit(1);
            } else {
                int status;
                wait(&status);

                if (WIFEXITED(status)) {
                    if (WEXITSTATUS(status) == 0) {
                        printf("Processus Fils : Petit-fils s'est terminé normalement.\n");
                    } else {
                        printf("Processus Fils : Petit-fils s'est terminé anormalement.\n");
                    }
                }
                exit(0);
            }
        } else {
            int status;
            wait(&status);

            if (WIFEXITED(status)) {
                if (WEXITSTATUS(status) == 0) {
                    printf("Processus Principal : mon fils s'est terminé normalement.\n");
                } else {
                    printf("Processus Principal : mon fils s'est terminé anormalement.\n");
                }
            }
        }
    }

    return 0;
}
