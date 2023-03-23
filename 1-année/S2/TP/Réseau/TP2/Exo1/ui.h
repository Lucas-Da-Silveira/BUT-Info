#ifndef __UI_H__

#define __UI_H__

#define UNCORRECT -1

#define ROND 1

#define RECTANGLE 2

#define CARRE 3


int whichForm(char* line); // renvoie -1/1/2/3 en fonction du contenu de line
int getCircleParams(char* line, double* radius);
int getRectangleParams(char* line, double* width, double* height);
int getSquareParams(char* line, double* side);

#endif