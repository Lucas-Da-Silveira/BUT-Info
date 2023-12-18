#include <stdio.h>
#include "listdouble.h"
 
int main(int argc, char** argv) {
 
  List* l;
 
  l = listCreate();
  listAppend(l,3);
  listAppend(l,5);
  listAppend(l,1);
  listInsert(l,15,1);
  listInsert(l,5,10); // indice > size
  listPrint(l);
  listRemoveAt(l,2);
  listRemove(l,3); // suppr head
  listRemove(l,5); // suppr queue
  listRemove(l,33); // suppr none
  listPrint(l);
  return 0;
}