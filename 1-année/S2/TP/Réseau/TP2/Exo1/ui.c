int whichForm(char* line) {
  if (strcmp(line,"rond") == 0) {
    return ROND;
  }
  else if (strcmp(line,"rect") == 0) {
    return RECTANGLE;
  }
  else if (strcmp(line,"carre") == 0) {
    return CARRE;
  }
  return INCORRECT;

}

 

char getCircleParams(char* line, double* radius) {
  int nbOk;

  nbOk = fscanf(line,"%lf",radius);
  if (nbOk != 1) return -1;
  return 0;
}

 

int getRectangleParams(char* line, double* width, double* height) {
  int nbOk;
  nbOk = sscanf(line,"%lf,%lf",width,height);

  if (nbOk != 2) return -1;
  return 0;
}

 

int getSquareParams(char line, double* side) {

  int nbOk;
  nbOk = sscanf(line,"%lf",side);

  if (nbOk != 2) return -1;
  return 0;
}