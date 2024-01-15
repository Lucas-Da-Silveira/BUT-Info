import java.util.*;

class Tree {

    public Node root;
    public int nbNodes; // pas nécessaire mais utile

    public Tree() {
	root = null;
	nbNodes = 0;
    }

    public Node addNode(int value, Node parent) {

	if (parent == null) {
	    Node n = new Node(value);
	    if (root != null) {
		n.addChild(root);
	    }
	    root = n;
	    nbNodes += 1;
	    return n;
	}
	else if (contains(parent,root) == parent) {
	    Node n = parent.addChild(value);
	    nbNodes += 1;
	    return n;
	}
	else {
	    return null;
	}
    }

    public Node contains(Node toSearch, Node parent) {

	//System.out.println("exploration du noeud contenant "+parent.value);
	if (parent == toSearch) {
	    return toSearch;
	}
	else if (parent.childs.size() == 0) {
	    return null;
	}
	else {
	    for(Node n : parent.childs) {
		Node ret = contains(toSearch,n);
		if (ret != null) return ret;
	    }
	}
	// si aucun sous-arbre partant des fils ne contient le noeud, renvoyer null
	return null; 
    }

    public Node searchValue(int value, Node parent) {

	if (parent.value == value) {
	    return parent;
	}

	Queue<Node> queue = new ArrayDeque<Node>();
	for(Node fils : parent.childs) {
	    queue.offer(fils);
	}

	while (!queue.isEmpty()) {
	    Node n = queue.poll();
	    System.out.println("evaluation du noeud contenant "+n.value);
	    if (n.value == value) {
		return n;
	    }
	    else {
		for(Node f : n.childs) {
		    queue.offer(f);
		}
	    }
	}
	return null;
    }

    public void print() {
	if (root != null) {
	    printNode(root,0);
	}
    }

    public void printNode(Node n,int level) {
	for(int i=0;i<2*level;i++) {
	    System.out.print(" ");
	}
	System.out.println(n.value);
	for(Node fils : n.childs) {
	    printNode(fils,level+1);
	}

    }
}
