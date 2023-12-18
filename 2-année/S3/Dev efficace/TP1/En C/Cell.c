#include <stdlib.h>

// Définition de la structure Cell
struct Cell {
    int value;           // La donnée ou la valeur stockée dans la cellule.
    struct Cell* next;   // Pointeur vers la prochaine cellule dans la liste chaînée.
};

// Fonction pour créer une nouvelle cellule avec une valeur donnée.
struct Cell* createCell(int value) {
    struct Cell* newCell = (struct Cell*)malloc(sizeof(struct Cell));
    if (newCell != NULL) {
        newCell->value = value;
        newCell->next = NULL;  // Initialement, il n'y a pas de prochaine cellule.
    }
    return newCell;
}