# Les Arbres

## <ins>Passon en profondeur d'abord</ins>

### Algo
```algo
type_retour parcours(Node a){
    //traitement 
    pour chaque fils f de a{
        type_retour val = parcours(f);
        si ... alors{}
        sinon{...}
        fini si ...
    }
    fini pour ...
    //traitement
    return type_retour;
}
```
### Java
```java
class Node{
    public List<Node> children;
    public int value;
    
    boolean contains(Node n, int val){
        if(n.value == val) return true;
        for(Node f : n.children){
            boolean rep = contains(f, val);
            if (rep) return true;
        }
        return false;
    }
    
    int treeDepth(Node n, int level){
        if(n.children.isEmpty()) return level;
        int maxDepth = 0;
        for(Node f : n.children){
            int d = treeDepth(f, level+1);
            if(d > maxDepth) maxDepth = d;
        }
        return maxDepth;
    }
    
    //AUtre façon treeDepth
    int treeDepth(Node n){
        if(n.children.isEmpty()) return 1;
        int maxDepth = 0;
        for(Node f : n.children){
            int d = treeDepth(f);
            if(d > maxDepth) maxDepth = d;
        }
        return maxDepth + 1;
    }
}
```

## <ins>Passon en longueur d'abord</ins>

### Java (non récursif)

```java
import java.util.ArrayDeque;
import java.util.ArrayList;

boolean contains(Node n, int val) {
    if (n.value == val) return true;
    Queue<Node> queue = new ArrayDeque<>();
    for (Node f : n.children) {
        queue.offer(f);
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.value == val) return true;
            for (Node f : current.children) {
                queue.offer(f);
            }
        }
    }
    return false;
}
```

## <ins>Recupérer la liste des noeuds d'un niveau donné </ins>

```java
public List<Node> getNodesForLevel(Node n, int level) {
    List<Node> list = new ArrayList<>();
    if (level == 0) {
        list.add(n);
    } else if (level > 0) {
        int curlevel = 1;
        Node separ = new Node();
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(separ);
        while (!queue.isEmpty()) {
            Node p = queue.poll();
            if (p = separ) {
                if (curlevel == level) {
                    return list;
                } else {
                    curlelvel++;
                    queue.offer(separ);
                }
            } else {
                if (curlevel == level) {
                    list.add(p);
                } else {
                    for (Node f : p.children) {
                        queue.offer(f);
                    }
                }
            }
            return list;
        }
    }
}
```

## <ins> Chercher la longueur max d'un arbre </ins>

```java
public int nbNodesByLevel(Node n, int level, int searchLevel){
    if(curLevel == searchLevel) return 1;
    int nb = 0;
    for (Node f : n.children){
        nb += nbNodesByLevel(f, curLevel+1, searchLevel);
    }
    return nb;
}

public int maxWidth(Node n){
    int max = 0;
    int width = 0;
    int level = 0;
    while((width = nbNodesByLevel(n, 0, level)) > 0){
        if(width > max) max = width;
        level++;
    }
    return max;
}
```

### En version plus efficace

```java
import java.util.List;

public int maxWidth(Node n) {
    int max = 0;
    List<Node> nbNodeByLevel = new ArrayList<>();
    CountByLevel(n, 0, nbNodeByLevel); //on commence à 1 pour le test sur la taille
    //trouver le max dans nbNodeByLevel
    return max;
}

public void CountByLevel(Node n, int level, List<Node> nbNodeByLevel) {
    if (nbNodes.size() < level) {
        nbNodes.add(1);
    }
    else {
        nbNodes.set(level, nbNodes.get(level) + 1);
    }
    for (Node child : n.children) {
        CountByLevel(child, level + 1, nbNodes);
    }
}
```

## <ins>Arbre linéaire ordonné</ins>

```java
class Node{
    Node left;
    Node right;
    int value;
    ...
    
    public void insert(int value){
        insertRecur(root,value);
    }
    
    public void insertRecur(Node n, int value) {
        if (n == null) {
            root = new Node(value);
        } else if (value < n.value) {
            if (n.left != null) {
                insertRecur(n.left, value);
            } else {
                n.left = new Node(value);
            }
        } else if (value > n.value) {
            if (n.right != null) {
                insertRecur(n.right, value);
            } else {
                n.right = new Node(value);
            }
        }
    }
    
    public Node search(int value){
        Node n = root;
        while(n != null && n.value != value){
            if(value <= n.value) n = n.left;
            else n = n.right;
        }
        return n;
    }
}
```
























