import java.util.*;
public class Node {
    public List<Node> children;
    public int value;

    public Node(int value) {
	this.value = value;
	children = new ArrayList<Node>();
    }

    /* addChild(int value) :
       créer un nouveau noeud contenant value
       et l'ajoute comme fils au noeud courant
       renvoie le nouveau noeud
     */
    public Node addChild(int value) {
        children.add(new Node(value));
        return children.get(children.size()-1);
    }

    /* addChild(Node n) :
       ajoute n comme fils au noeud courant
     */
    public void addChild(Node n) {
        children.add(n);
    }

    /* getChild(int index) :
       si index <0 ou >= taille list, renvoie null
       sinon renvoie le fils en index

     */
    public Node getChild(int index) {
        if (index < 0 || index >= children.size()) {
            return null;
        }
        return children.get(index);
    }
}
