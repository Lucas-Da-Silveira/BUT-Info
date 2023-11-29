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
        public class Main{
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