typedef struct cell {
  int value;
  struct cell* next;
  struct cell* prev;
} Cell;
 
typedef struct list {
  Cell* head;
  int size;
} List;
 
List* listCreate();
Cell* listFind(List* l, int value);
Cell* listGet(List* l, int index);
Cell* listPrepend(List* l, int value);
Cell* listAppend(List* l, int value);
Cell* listInsert(List* l, int value, int index);
Cell* listReplace(List* l, int value, int index);
Cell* listRemoveAt(List* l, int index);
Cell* listRemove(List* l, int value);
void listPrint(List* l);