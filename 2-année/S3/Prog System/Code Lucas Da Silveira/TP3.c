#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/stat.h>
#include <sys/types.h>

#define TAILLE_TAMPON 4096

int main(int argc, char *argv[])
{
    if (argc < 2) {
        fprintf(stderr, "Usage: %s fichier\n", argv[0]);
        return EXIT_FAILURE;
    }

    const char *nom = argv[1];
    char tampon[TAILLE_TAMPON];
    int fich, nlus, necrits;
    int total = 0;
    fich = open(nom, O_RDONLY);
    if (fich < 0) {
        perror("Ouverture du fichier");
        return EXIT_FAILURE;
    }
    do {
        nlus = read(fich, tampon, TAILLE_TAMPON);
        if (nlus < 0) {
            perror("Erreur de lecture");
            close(fich);
            return EXIT_FAILURE;
        }
        total += nlus;
        necrits = write(1, tampon, nlus);
        if (necrits != nlus)
            fprintf(stderr, "%d caractères écrits au lieu de %d lus\n",
                    necrits, nlus);
    } while (nlus != 0);
    fprintf(stderr, "%d caractères du fichier %s ont été copiés\n",
            total, nom);
    close(fich);
    return EXIT_SUCCESS;
}