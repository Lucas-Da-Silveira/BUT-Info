#include <stdio.h>
#include <stdlib.h>

// Définition de la structure Cell
struct Cell {
    int value;
    struct Cell* next;
};

// Définition de la structure List
struct List {
    struct Cell* head;
    int size;
};

// Fonction pour initialiser une nouvelle liste
void initializeList(struct List* list) {
    list->head = NULL;
    list->size = 0;
}

// Fonction pour trouver une cellule avec une valeur donnée
struct Cell* find(struct List* list, int value) {
    struct Cell* c = list->head;
    while ((c != NULL) && (c->value != value)) {
        c = c->next;
    }
    return c;
}

// Fonction pour obtenir la cellule à un certain index
struct Cell* get(struct List* list, int index) {
    struct Cell* c = list->head;
    int i = 0;

    if (index < 0 || index >= list->size) {
        return NULL;
    }

    while ((c != NULL) && (i < index)) {
        c = c->next;
        i++;
    }
    return c;
}

// Fonction pour ajouter une nouvelle cellule à la fin de la liste
struct Cell* append(struct List* list, int value) {
    struct Cell* c = NULL;
    struct Cell* newCell = (struct Cell*)malloc(sizeof(struct Cell));
    newCell->value = value;
    newCell->next = NULL;

    if (list->size == 0) {
        list->head = newCell;
    } else {
        c = get(list, list->size - 1);
        c->next = newCell;
    }

    list->size += 1;
    return newCell;
}

// Fonction pour insérer une nouvelle cellule à un certain index
struct Cell* insert(struct List* list, int value, int index) {
    struct Cell* c = NULL;
    struct Cell* newCell = (struct Cell*)malloc(sizeof(struct Cell));
    newCell->value = value;

    if (index > list->size) {
        index = list->size;
    }
    if (index < 0) {
        index = 0;
    }

    if (list->size == 0) {
        list->head = newCell;
    } else if (index == 0) {
        newCell->next = list->head;
        list->head = newCell;
    } else {
        c = get(list, index - 1);
        newCell->next = c->next;
        c->next = newCell;
    }

    list->size += 1;
    return newCell;
}

// Fonction pour remplacer la valeur d'une cellule à un certain index
struct Cell* replace(struct List* list, int value, int index) {
    if (index < 0 || index >= list->size) {
        return NULL;
    }

    struct Cell* c = get(list, index);
    c->value = value;

    return c;
}

// Fonction pour supprimer une cellule à un certain index
struct Cell* removeAt(struct List* list, int index) {
    struct Cell* c = NULL;
    struct Cell* p = NULL;

    if (index < 0 || index >= list->size) {
        return NULL;
    }

    if (index == 0) {
        list->head = list->head->next;
    } else {
        p = get(list, index - 1);
        c = p->next;
        p->next = c->next;
    }

    list->size -= 1;
    return c;
}

// Fonction pour supprimer une cellule avec une valeur donnée
struct Cell* removeValue(struct List* list, int value) {
    struct Cell* c = list->head;
    struct Cell* p = NULL;

    while ((c != NULL) && (c->value != value)) {
        p = c;
        c = c->next;
    }

    if (c != NULL) {
        if (c == list->head) {
            list->head = c->next;
        } else {
            p->next = c->next;
        }
        list->size -= 1;
    }
    return c;
}

// Fonction pour afficher la liste
void printList(struct List* list) {
    struct Cell* c = list->head;
    while (c != NULL) {
        printf("%d\n", c->value);
        c = c->next;
    }
}

int main() {
    struct List myList;
    initializeList(&myList);

    append(&myList, 20);
    insert(&myList, 10, -5);
    insert(&myList, 30, 7);
    append(&myList, 50);
    insert(&myList, 40, 3);
    printList(&myList); // Affiche 10 20 30 40 50

    removeValue(&myList, 12);
    removeAt(&myList, -2);
    removeAt(&myList, 22);
    removeAt(&myList, 2);
    printList(&myList); // Affiche 20 30 50

    struct Cell* c = get(&myList, -1);
    if (c != NULL) {
        printf("pb with get()\n");
    }

    c = get(&myList, 99);
    if (c != NULL) {
        printf("pb with get()\n");
    }

    c = find(&myList, 99);
    if (c != NULL) {
        printf("pb with find()\n");
    }

    c = find(&myList, 20);
    if (c->value != 20) {
        printf("pb with find()\n");
    }

    c = find(&myList, 50);
    if (c->value != 50) {
        printf("pb with find()\n");
    }

    return 0;
}