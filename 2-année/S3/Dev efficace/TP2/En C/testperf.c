#include <stdio.h>
#include <stdlib.h>
#include "listdouble.h"
#include <sys/time.h>
#include <time.h>
#include <math.h>
#include <string.h>

#define CLOCK_S 0
#define CLOCK_MS 1
#define CLOCK_US 2

double dclock(int mode) {
    double time;
    struct timeval t;

    gettimeofday(&t, NULL);
    if (mode == CLOCK_US) {
        time = 1.0e6 * (double) t.tv_sec + (double) t.tv_usec;
    } else if (mode == CLOCK_MS) {
        time = 1.0e3 * (double) t.tv_sec + 1.0e-3 * (double) t.tv_usec;
    } else if (mode == CLOCK_S) {
        time = (double) t.tv_sec + 1.0e-6 * (double) t.tv_usec;
    }

    return (time);
}

int main(int argc, char **argv) {

    if (argc != 2) {
        fprintf(stderr, "usage : testperf nb_cell\n");
        exit(1);
    }
    int nbCell = atoi(argv[1]);

    List *l1;
    List *l2;
    List *l3;
    int i;
    l1 = listCreate();
    l2 = listCreate();
    l3 = listCreate();

    printf("liste doublement chaînée circulaire, insertion début ...");
    double t = dclock(CLOCK_S);
    for (i = 0; i < nbCell; i++) {
        listPrepend(l1, i + 1);
    }
    t = dclock(CLOCK_S) - t;
    printf("%fs\n", t);

    printf("liste doublement chaînée circulaire, insertion milieu ...");
    t = dclock(CLOCK_S);
    for (i = 0; i < nbCell; i++) {
        listInsert(l2, i + 1, i / 2);
    }
    t = dclock(CLOCK_S) - t;
    printf("%fs\n", t);

    printf("liste doublement chaînée circulaire, insertion fin ...");
    t = dclock(CLOCK_S);
    for (i = 0; i < nbCell; i++) {
        listAppend(l3, i + 1);
    }
    t = dclock(CLOCK_S) - t;
    printf("%fs\n", t);

    return 0;
}