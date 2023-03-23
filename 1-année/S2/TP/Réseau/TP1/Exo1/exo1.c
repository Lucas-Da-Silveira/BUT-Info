#include <stdio.h>

#include <stdlib.h>

#include <string.h>

void usage() {

   fprintf(stderr,"usage : myprog ip port\n\t- ip : A.B.C.D (with 0<A,B,C,D<255)\n\t- port : from 0 to 65535\n");

   exit(1); // termine l'exécution

}

int main(int argc, char**argv) {

   int p1,p2,p3,p4;

   int nbOk = sscanf(argv[1],"%d.%d.%d.%d",&p1,&p2,&p3,&p4);

   if (nbOk != 4) usage();

   if ((p1<0)||(p1>255)||(p2<0)||(p2>255)||(p3<0)||(p3>255)||(p4<0)||(p4>255)) usage();


   printf("ip : %d.%d.%d.%d, port : %d\n",p1,p2,p3,p4,port);

   return 0;

}