#include <stdio.h>

#include <stdlib.h>

#include <string.h>

#include "main.h"

#include "ui.h"

#include "calcul.h"

 

void usage() {

  fprintf(stderr,"usage : formcalc\n");

  exit(1);

}

 

void trimLast(char* line) {

  if (line[strlen(line)-1] == '\n') {

    line[strlen(line)-1] = '\0';

  }

}

 

int main(int argc, char** argv) {

 

  char line[1024] = " ";

  int numForm;

  double rayon,hauteur,largeur;

  int ret;

 

  // arrêt si ligne vide

  while (strcmp(line,"") != 0) {

    printf("forme : ");

    fgets(line,1024,stdin);

    trimLast(line);

    numForm = whichForm(line);

 

    if (numForm == UNCORRECT) {

      continue;

    }

    else if (numForm == ROND) {

      printf("rayon : ");

    }

    else if (numForm == RECTANGLE) {

      printf("largeur,hauteur : ");

    }

    else if (numForm == CARRE) {

      printf("côté : ");

    }  

 

    // saisie caractéristique forme

    fgets(line,1024,stdin);

    trimLast(line);

 

    if (numForm == ROND) {

      ret = getCircleParams(line,&rayon);

      if (ret == 0) {

              printf("rond de rayon %f, perimetre = %f, aire = %f\n",rayon,perimetreRond(rayon), aireRond(rayon));

      }

      else {

              printf("saisie inccorecte\n");

      }

    }
    else if (numForm == RECTANGLE) {

      ret = getRectangleParams(line,&largeur,&hauteur);

      if (ret == 0) {

              printf("rectangle %fx%f (LxH), perimetre = %f, aire = %f\n",largeur, hauteur,perimetreRectangle(largeur,hauteur), aireRectangle(largeur,hauteur));
      }

      else {
              printf("saisie inccorecte\n");

      }
    }
    else if (numForm == CARRE) {

      ret = getSquareParams(line,&largeur);

      if (ret == 0) {
              printf("carré de côté %f, perimetre = %f, aire = %f\n",largeur,perimetreCarre(largeur), aireCarre(largeur));
      }
      else {
              printf("saisie inccorecte\n");

      }     

    }  

  }

}