import numpy as np
import math
import matplotlib.pyplot as plt



#============= Étude d'une suite==================

u0 = 4
u1 = 1.75
u2 = 2.71
u3 = 2.10
u4 = 2.45
u5 = 2,237113402

def u1_iteratif(n):
    u = 4
    for i in range (n):
        u = (3/u)+1
    return u


def u1_recursif(n):
    if n == 0:
        return 4
    else:
        return (3/u1_recursif(n-1))+1
    

def affiche(f,xmin,xmax,pas):
    tabx = np.arange(xmin,xmax,pas)
    taby = []
    for x in tabx:
        taby.append(f(x))
    plt.plot(tabx,taby)
    plt.grid()
    plt.show()

# for i in range(100):
#     print("u1_iteratif(",i,")=",u1_iteratif(i))

# affiche(u1_iteratif,0, 100, 1)
# affiche(u1_iteratif,15, 20, 1)
# affiche(u1_iteratif,18, 25, 1)
# affiche(u1_iteratif,60, 65, 1)

#u semble décroissante
#Il y a oscillation de u0 jusqu'a u60 == amortissement de la courbe
#Je conjecture une convergence vers 2.302775637731995


# ================= Q2=============

def convergence(f,u0,rang,deb,fin,pas):
    u=u0
    tabx = np.arange(deb,fin,pas)
    tabf = []
    ux=[]
    uy=[]
    for x in tabx:
        tabf.append(f(x))
    for n in range(rang+1):
        ux.append(u)
        ux.append(u)
        uy.append(u)
        uy.append(f(u))
        u=f(u)
    plt.plot(tabx,tabx)
    plt.plot(tabx,tabf) 
    plt.plot(ux,uy)
    plt.grid()
    plt.show()


#================ Étude d'une fonction ===========

def zero(f,xmin,xmax,precision):
    while xmax-xmin>precision:
        xmil=(xmin+xmax)/2
        if f(xmil)==0:
            return xmil
        elif f(xmil)*f(xmin)<0:
            xmax=xmil
        else:
            xmin=xmil
    return xmil


def max(f,xmin,xmax):
    def fprime(x):
        return derive(f,x,0.00000001)
    x0 = zero(fprime,xmin,xmax,0.000000000000001)
    y0 = f(x0)
    return x0,y0

def f(x):
    return (x**5)-(6.38238*(x**4))-(2486.42*(x**3))+(15946.3*(x**2))-(33945.6*x)+24072.5

affiche(f,-1000,1000,0.01)
affiche(f,-40,-30,0.01)
affiche(f,0,40,0.01)
affiche(f,35,43,0.01)

print(zero(f,-40,50,0.01))


#C'est une fonction cube
#La fonction semble croissante de - l'infini à + l'infini 
#La fonction est décroissante de - 38 à 39 
#La fonction coupe en zéro à 2.0831298828125
