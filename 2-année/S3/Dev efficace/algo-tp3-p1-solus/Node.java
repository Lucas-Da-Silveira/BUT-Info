import java.util.*;

class Node {

    public List<Node> childs;
    public int value;

    public Node(int value) {
	this.value = value;
	childs = new ArrayList<Node>();
    }

    /* addChild(int value) :
       créer un nouveau noeud contenant value
       et l'ajoute comme fils au noeud courant
       renvoie le nouveau noeud
     */
    public Node addChild(int value) {
	Node n = new Node(value);
	childs.add(n);
	return n;
    }

    /* addChild(Node n) :
       ajoute n comme fils au noeud courant
     */
    public void addChild(Node n) {
	childs.add(n);
    }

    public Node getChild(int index) {
	if ((index <0) || (index >= childs.size())) {
	    return null;
	}
	return childs.get(index);
    }

}
