# Collection:

## Structure de donées : 

- Set : enssemble d'objets/des données non indexé, sans doublon


- Lint : enssemble d'objets, indéxé, eventuellement avec des doublons


- Map : enssemble associatif d'objet, non indexé, chaque objet etant associé à une clé. 
Une clé est unique dans un Map mais plusieurs clés peuvent être associé a un même objet.


- Queue : ensemble d'objets non indexé, avec un schéma d'ecxès de type FIFO (First In First Out), LIFO (Last In First Out).

En réalité, Set, Map, Lint et Queue sont des classe ditent interfaces.


## Méthodes communes: 

- int size()
- void (clear)
- boolean isEmpty()

## HandSet
```java
Set `<MaClasse>` set = new HashSet`<>`();
```
- boolean add(E e): ajoute l'objet e de la classe E
- boolean remove(Object o): supprime l'objet o s'il existe sinon renvoie false
- boolean contains(Object o): renvoie true si l'objet o existe sinon renvoie false
- Iterator`<E>` iterator(): retourne un itérateur sur le Set

```java
public class A(...........){
    public class B(........){
        public class TestPerf{
            .............
            Set`<A>` set = new HashSet`<>`();
            A = new A(..........);
            B = new B(..........);
            Date d = new Date();
            set.add(a);
            set.add(b);
            set.add(d); //eurreur de compilation
            
            boolean rep;
            rep = set.contains(a); //true
            rep = set.contains(b); //false
            rep = set.contains(d); //false
        }
    }
}
```

## ArrayList

```java
List`<MaClasse>` list = new ArrayList`<>`();
```
- boolean add(E e): ajoute l'objet e en fin de liste
- void add(int index, E element): insertion en index. Si index < 0 ou > taille liste, ne fait rien et lève une exception
- E get(int index): renvoie l'élément en index
- int indexOf(Object o): renvoie l'index de o
- E remove(int index): supprime l'élément en index et renvoie l'élément supprimé

```java
List<A> list = new ArrayList<>();
A a1 = new A(..........);
A a2 = new A(..........);

list.add(a1);
list.add(a2);

list.add(A); // erreur comilation
list.add(15, a1); //erreur exception

A aa = null;
aa = list.get(0); //référence a1
aa = remove(0); //référence a2

int pos = list.indexOf(a1); //return 0
pos = list.indexOf(a2); //return 1
```

## HashMap

```java
Map <Interger, String> map = new HashMap<>();
```
- V put(K key, V value): ajoute/modifie un couple {clé, valeur}. Si la clé existe déjà, la valeur est remplacée
- V get(Object key): renvoie la valeur associée à la clé key si elle existe déjà sinon return null
- boolean containsKey(Object key); : Renvoi true si la clé existe, sinon renvoi false
- boolean containsValue(Object value); : Renvoi true si la valeur existe, sinon renvoi false
- V remove(Objet, Key) : supprime la valeur associé à la clé

```java
Set<k> Keyset();
```

```java
Map <String, A> map1 = new HashMap<>();
Map <A, integer> map2 = new HashMap<>();

A a1 = new A(...);
A a2 = new A(...);
Date = new Date();

map1.put("toto", a1);
map1.put("tata", a2); //Ok événement de la valeur précédente
map1.put(a1, "tutu"); //erreur de compilation

map2.put(a1, new Integer(10));
map2.put(d, new Integer(5)); //erreur de compilation
map2 containsKey(d); //false
map2 containsKey(a1); //true
map2.remove(d); //ne fait rien
map2.remove("toto"); //renvoie a1        
map2.remove(a1); //supprime la valeur associé à a1
```


## ArrayDeque

```java
Queue`<String>` queue = new ArrayDeque`<>`();
```
### File :
- boolean offer(E e): ajoute l'élément e en fin de file
- E poll(): supprime et renvoie l'élément en tête de file. Si la file est vide, renvoie null

### Pile :
- void push(E e): ajoute l'élément e en tête de pile
- E pop(): supprime et renvoie l'élément en tête de pile. Si la pile est vide, renvoie null

```java
Queue<Double> q = new ArrayDeque<>();
//Fifo 
q.offer(1.0);
q.offer(2.0);
double val = q.poll(); //val = 1.0

//Lifo
q.push(3.0);
double val = q.pop(); //val = 3.0
double val = q.pop(); //val = 2.0
```

### Autre méthode:
```java
boolean offerFirst = q.offer(E e); //ajoute en tête de file
boolean offerLast = q.offerLast(E e); //ajoute en fin de file
void addFirst(E e); //ajoute en tête de file
void addLast(E e); //ajoute en fin de file
E getFirst(); //Renvoi l'élément en tête de file
E getLast(); //renvoie l'élément en fin de file
E peekFirst(); // Renvoi l'élément en tête de file, renvoi null si la file est vide
E peekLast(); //renvoie l'élément en fin de file
```

## Parcours d'une collection

- Boucle for MAIS on se modifie pas la collection :
  - Pas d'ajout/suppression d'élément

### For:
```java
Set<String> set = new HashSet<>();
List<Date> list = new ArrayList<>();
Map<Interger,String> map = new HashMap<>();

for (String s : set){
    s...
}
for (Date d : list){
    d...
}
for (Map.Entry<Integer, String> e : map.entrySet()){
    Integer key = e.getKey();
    String value = e.getValue();
}
```

### Iterator:
```java
Set<String> set = new HashSet<>();
List<Date> list = new ArrayList<>();
Map<Interger,String> map = new HashMap<>();

Iterator<String> is = set.iterator();
while (is.hasNext()){
    String s = is.next();
    ...
    if(...) is.remove();
    ...
}

ListIterator<Date> il = list.listIterator();
while (il.hasNext()){
    Date d = il.next();
    ... //il parcoure la liste dans un sens
    // il.parcour il.add(E e) il.remove() il.set(E e)
}

Iterator<Integer> im = map.keySet();
while (im.hasNext()){
    Integer key = im.next();
    String value = map.get(key);
    ...
}
```

## Utilisation:
```java
Map<Integer, Double> map = new HashMap<>();
map.get(5,1,2,3,4);
double val = map.get(5); //val contient = 1,2,3,4

Double d = 1.1111
if(d == 1.1111) //false
``` 
```java
Integer i1 = 10; 
Integer i2 = 10;
Integer i3 = new Integer(10);
if(i1 == i2){
    System.out.println("egal 1"); //s'affiche
        }
if(i1 == i3){
    System.out.println("egal 2"); //ne s'affiche pas
        }
```