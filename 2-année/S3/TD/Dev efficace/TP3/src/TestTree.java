class TestTree {

    public static void main(String[] args) {

	Tree tree = new Tree();

	Node root = tree.addNode(20,null); // création racine
	root = tree.addNode(10,null); // test remplacement racine
	Node n1 = tree.addNode(21,root);
	n1.addChild(30);
	Node n2 = n1.addChild(31);
	Node n3 = n1.addChild(32);
	n2.addChild(40);
	n2 = n2.addChild(41);
	n3.addChild(45);
	n3.addChild(46);
	Node n4 = n3.addChild(47);
	n4.addChild(50);
	n4 = n4.addChild(51);

	tree.print();


	Node s = tree.contains(n3,root);
	if (s != n3) {
	    System.out.println("echec recherche du noeud contenant 32");
	}
	s = tree.contains(n2,root);
	if (s != n2) {
	    System.out.println("echec recherche du noeud contenant 41");
	}
	s = tree.contains(n4,root);
	if (s != n4) {
	    System.out.println("echec recherche du noeud contenant 51");
	}

	s = tree.searchValue(45,1); // recherche en profondeur
	if (s.value != 45) {
	    System.out.println("echec recherche valeur 45");
	}
	s = tree.searchValue(50,2); // recherche en largeur
	if (s.value != 50) {
	    System.out.println("echec recherche valeur 50");
	}
	s = tree.searchValue(60,1); // recherche en profondeur
	if (s != null) { // si on trouve qq chose = pas normal
	    System.out.println("echec recherche valeur 60");
	}
    }
}