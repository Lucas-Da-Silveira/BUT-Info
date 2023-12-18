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