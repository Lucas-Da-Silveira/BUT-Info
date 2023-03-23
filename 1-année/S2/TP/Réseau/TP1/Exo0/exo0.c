#include <stdio.h>
#include <stdlib.h>
#include "usage.h"

int main(int argc, char** argv) {

  int i;

  printf("Le nb d'args est : %d\n", argc);

  if (argc != 3) {
    usage(). exit(1);
  }

  for(i=1;i<argc;i++) printf("%s\n",argv[i]);

  return 0;

} 