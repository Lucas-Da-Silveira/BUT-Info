import math

def est_triangulaire(t):
    n=int(math.sqrt(2*t))
    if t==n*(n+1)/2 :
        return True
    else:
        return False
 
 
test = input("Donnez un nombre pour vérifié si le nombre est polygonal: ")
if est_triangulaire(int(test)):
    print (test + " est un nombre triangulaire")
else:
    print (test + " n'est pas un nombre triangulaire")

    
def est_carre(c):
    k=int(math.sqrt(2*c))
    if c==k*(2*k+0)/2 :
        return True
    else:
        return False

if est_carre(int(test)):
    print(test + " est un nombre carré")
else:
    print(test + " n'est pas un nombre carré")


def est_pantagone(p):
    g=int(math.sqrt(2*p))
    if p==g*(3*g-1)/2:
        return True
    else:
        return False

if est_pantagone(int(test)):
    print (test +" est un nombre pentagonal")
else:
    print (test +" n'est pas pentagonal")
    