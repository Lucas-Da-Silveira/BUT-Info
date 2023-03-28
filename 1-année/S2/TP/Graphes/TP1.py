import networkx as nx
import matplotlib.pyplot as plt

G = nx.Graph()

G.add_node(1)
G.add_nodes_from([2,3,4])

G.add_edge(1,2)
G.add_edges_from([(1,3),(1,4),(2,3)])
G.add_edge(1,5)

# nx.draw(G, with_labels=True, node_color='yellow')
# plt.show()  

print(G.nodes())

print(G.number_of_nodes())

print(G.degree(1))

degrees = G.degree()

print(sum(dict(degrees).values())/ G.number_of_nodes())


A = nx.random_regular_graph(2, 10)


def regulier(G):
    degrees = G.degree()
    for i in degrees:
        if i[1] != 2:
            return False
    return True

def regulier(A):
    degrees = A.degree()
    for i in degrees:
        if i[1] != 2:
            return False
    return True

def regulier(B):
    degrees = B.degree()
    for i in degrees:
        if i[1] != 2:
            return False
    return True


print(regulier(G))
print(regulier(A))

print(A)

print(A.edges())

print(A.number_of_edges())

print(nx.diameter(A))


B = nx.Graph()
B.add_nodes_from([1,2,3,4,5,6])
B.add_edges_from([(1,2),(1,5),(1,4),(2,3),(2,5),(2,4),(3,6),(3,4),(3,5),(4,5),(4,6)])
# nx.draw(B, with_labels=True, node_color='yellow')
# plt.show()

def stats():
    print("Nombre de noeuds : ", B.number_of_nodes())
    print("Nombre d'arêtes : ", B.number_of_edges())
    print("Degré moyen : ", sum(dict(B.degree()).values())/ B.number_of_nodes())
    print("Diamètre : ", nx.diameter(B))
    print("Graph régulier : ",regulier(B))

stats()

print("=====G======")
print(nx.adjacency_matrix(G).toarray())
print("=====A======")
print(nx.adjacency_matrix(A).toarray())
print("=====B======")
print(nx.adjacency_matrix(B).toarray())