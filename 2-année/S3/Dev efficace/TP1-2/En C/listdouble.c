#include <stdio.h>
#include <stdlib.h>
#include "listdouble.h"

List* listCreate() {
    List* newList = (List*)malloc(sizeof(List));
    if (newList == NULL) {
        exit(EXIT_FAILURE);
    }

    newList->head = NULL;
    newList->size = 0;

    return newList;
}

Cell* listFind(List* l, int value) {
    Cell* current = l->head;
    while (current != NULL && current->value != value) {
        current = current->next;
    }
    return current;
}

Cell* listGet(List* l, int index) {
    if (index < 0 || index >= l->size) {
        return NULL;
    }

    Cell* current = l->head;
    while (index > 0) {
        current = current->next;
        index--;
    }

    return current;
}

Cell* listPrepend(List* l, int value) {
    Cell* newCell = (Cell*)malloc(sizeof(Cell));
    if (newCell == NULL) {
        exit(EXIT_FAILURE);
    }

    newCell->value = value;
    newCell->next = l->head;
    newCell->prev = NULL;

    if (l->head != NULL) {
        l->head->prev = newCell;
    }

    l->head = newCell;
    l->size++;

    return newCell;
}

Cell* listAppend(List* l, int value) {
    Cell* newCell = (Cell*)malloc(sizeof(Cell));
    if (newCell == NULL) {
        exit(EXIT_FAILURE);
    }

    newCell->value = value;
    newCell->next = NULL;

    if (l->head == NULL) {
        newCell->prev = NULL;
        l->head = newCell;
    } else {
        Cell* last = l->head;
        while (last->next != NULL) {
            last = last->next;
        }
        newCell->prev = last;
        last->next = newCell;
    }

    l->size++;

    return newCell;
}

Cell* listInsert(List* l, int value, int index) {
    if (index < 0) {
        return listPrepend(l, value);

    } else if (index >= l->size) {
        return listAppend(l, value);

    } else {
        Cell* current = listGet(l, index);
        Cell* newCell = (Cell*)malloc(sizeof(Cell));
        if (newCell == NULL) {
            exit(EXIT_FAILURE);
        }

        newCell->value = value;
        newCell->next = current;
        newCell->prev = current->prev;
        current->prev->next = newCell;
        current->prev = newCell;

        l->size++;
        return newCell;
    }
}

Cell* listReplace(List* l, int value, int index) {
    Cell* cellToReplace = listGet(l, index);
    if (cellToReplace != NULL) {
        cellToReplace->value = value;
    }
    return cellToReplace;
}

Cell* listRemoveAt(List* l, int index) {
    if (index < 0 || index >= l->size) {
        return NULL;
    }

    Cell* removedCell;

    if (index == 0) {
        removedCell = l->head;
        l->head = l->head->next;
        if (l->head != NULL) {
            l->head->prev = NULL;
        }

    } else {
        Cell* current = listGet(l, index);
        removedCell = current;

        current->prev->next = current->next;
        if (current->next != NULL) {
            current->next->prev = current->prev;
        }
    }

    l->size--;
    return removedCell;
}

Cell* listRemove(List* l, int value) {
    Cell* cellToRemove = listFind(l, value);
    if (cellToRemove != NULL) {
        int index = 0;
        Cell* current = l->head;
        while (current != cellToRemove) {
            current = current->next;
            index++;
        }

        listRemoveAt(l, index);
    }

    return cellToRemove;
}

void listPrint(List* l) {
    Cell* current = l->head;
    while (current != NULL) {
        printf("%d ", current->value);
        current = current->next;
    }
    printf("\n");
}