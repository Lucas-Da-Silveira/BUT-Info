import networkx as nx
import matplotlib.pyplot as plt

G = nx.Graph()

G.add_node(1)
G.add_nodes_from([2,3,4])

G.add_edge(1,2)
G.add_edges_from([(1,3),(1,4),(2,3)])
G.add_edge(1,5)

nx.draw(G, with_labels=True, node_color='yellow')
plt.show()  

print(G.nodes())

print(G.number_of_nodes())

print(G.degree(1))

degrees = G.degree()

print(sum(dict(degrees).values())/ G.number_of_nodes())