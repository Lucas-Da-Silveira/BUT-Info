import java.util.*;

class Tree {

    public Node root;
    public int nbNodes; // pas nécessaire mais utile

    public Tree() {
        root = null;
        nbNodes = 0;
    }

    public Node addNode(int value, Node parent) {
	/*
 
	  - si parent est null :
	     - créer un nouveau noeud contenant value
	     - si root existe déja : ajouter root comme fils du nouveau noeud
	     - root devient le nouveau noeud
	     - incrémenter le nombre de noeud
	     - renvoyer le nouveau noeud
	  - sinon si l'arbre contient parent
	     - ajouter un noeud fils à parent, contenant value
	     - incrémenter le nombre de noeud
	     - renvoyer le nouveau noeud
	  - sinon renvoyer null
	*/
        if (parent == null) {
            Node n = new Node(value);
            if (root != null) {
                n.addChild(root);
            }
            root = n;
            nbNodes++;
            return n;
        } else if (contains(parent, root) != null) {
            Node n = new Node(value);
            parent.addChild(n);
            nbNodes++;
            return n;
        } else {
            return null;

        }
    }

    public Node contains(Node toSearch, Node parent) {

        if (parent == null) {
            return null;
        } else if (parent == toSearch) {
            return parent;
        } else {
            for (int i = 0; i < parent.children.size(); i++) {
                Node n = contains(toSearch, parent.children.get(i));
                if (n != null) {
                    return n;
                }
            }
            return null;
        }
    }

    // NB : la recherche doit se faire en profondeur d'abord

    public Node searchValueByLevel(int value, Node parent) {
        // NB : la recherche doit se faire en largeur d'abord

        if (parent == null) {
            return null;
        } else if (parent.value == value) {
            return parent;
        } else {
            for (int i = 0; i < parent.children.size(); i++) {
                Node n = searchValueByLevel(value, parent.children.get(i));
                if (n != null) {
                    return n;
                }
            }
            return null;
        }
    }

    public Node searchValueByDepth(int value, Node parent) {

        // NB : la recherche doit se faire en profondeur d'abord */
        if (parent == null) {
            return null;
        } else if (parent.value == value) {
            return parent;
        } else {
            for (int i = 0; i < parent.children.size(); i++) {
                Node n = searchValueByDepth(value, parent.children.get(i));
                if (n != null) {
                    return n;
                }
            }
            return null;
        }
    }

    public Node searchValue(int value, int type) {
        Node n = null;
        if (type == 1) n = searchValueByDepth(value, root);
        else if (type == 2) n = searchValueByLevel(value, root);
        return n;
    }

    public void print() {
        if (root != null) {
            System.out.println("affichage de l'arbre en profondeur\n");
            printNode(root, 0);
            System.out.println("\naffichage de l'arbre en largeur\n");
            printLevel();
        }
    }

    public void printNode(Node n, int level) {
	/*
	  - afficher 2*level espace puis la valeur contenue dans n
	  - pour chaque noeud fils de n :
	     - afficher le contenu du noeud, en incrémentant le niveau
	*/
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
        System.out.println(n.value);
        for (int i = 0; i < n.children.size(); i++) {
            printNode(n.children.get(i), level + 1);
        }
    }

    public void printLevel() {
        if (root == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.print(current.value + " ");

            queue.addAll(current.children);
        }
    }

}