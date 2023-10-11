#include <stdio.h>
#include <unistd.h>

/*int main() {
    char *args[] = {"xterm", NULL};

    execvp("xterm", args);

    perror("execvp");

    return 1;
}*/

//prog 2

/*int main(int argc, char *argv[]) {
    if (argc < 2) {
        fprintf(stderr, "Usage: %s <command>\n", argv[0]);
        return 1;
    }

    execlp(argv[1], argv[1], NULL);

    perror("execlp");

    return 1;
}*/

//prog 3

int main() {

    execlp("sudo", "sudo", "bash", NULL);

    perror("execlp");

    return 1;
}

