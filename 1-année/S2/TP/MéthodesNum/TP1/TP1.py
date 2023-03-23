import numpy as np
import math
import matplotlib.pyplot as plt
#----------U1-----------

# u0 = 2.56453
# u1 = 2126,117349
# u2 = 4243,724221
# u3 = 6355,461793
# u4 = 8461,166668

def u1_iteratif(n):
    u = 2.56453
    for i in range (n):
        u = 0.9972*u+2123.65
    return u


def u1_recursif(n):
    if n == 0:
        return 2.56453
    else:
        return 0.9972*u1_recursif(n-1)+2123.65

# for i in range (4):
#     print(i, u1_iteratif(i), u1_recursif(i))
#u semble strictement croissante
# ça démare fort mais sa ralentit
# Convergence ?



# for i in range (11000, 11100):
#     print(i, u1_iteratif(i),)

# Stabilisation vers 11096


# i =100
# while (u1_iteratif(i+1) - u1_iteratif(1)>0):
#     i += 1
# print(i)



#----------U2-----------
# u0 = 2.5645
# u1 = 2.557359316
# u2 = 3.550188738
# u3 = 7.540248209
# u4 = 16.51913551

def u2_iteratif(n):
    u = 2.5645
    for i in range (n):
        u = 0.9972*u+i*i
    return u


def u2_recursif(n):
    if n == 0:
        return 2.5645
    else:
        return 0.9972*u2_recursif(n-1)+(n-1)**2

# for i in range (5):
#     print(i, u2_iteratif(i), u2_recursif(i))


# for i in range (100):
#     print(i, u2_iteratif(i))
# U semble strictement croissenate sauf pour le premier terme 
# Ça accélère 
# Divergence vers + l'infini ??

# for i in range (100, 1000):
#     print(i, u2_iteratif(i+1) - u2_iteratif(i))
# Les écarts augmentent vite
# Je conjecture une Divergence vers + l'infini


#----------U3-----------

# u0 = 0
# u1 = 1
# u2 = 1
# u3 = 2
# u4 = 3
# u5 = 5
# u6 = 8
# u7 = 13

def u3_iteratif(n):
    if n < 2:
        return n
    a = 0
    b = 1
    for i in range (n-1):
        c = a+b
        a = b
        b = c
    return c


def u3_recursif(n):
    if n < 2:
        return n
    else:
        return u3_recursif(n-1)+u3_recursif(n-2)

# for i in range (8):
#     print(i,u3_iteratif(i), u3_recursif(i))

# for i in range (100):
#     print(i, u3_iteratif(i))

# U semble strictement croissenate sauf pour les 2 premiers termes
# Ça accélère
# Divergence vers + l'infini ??

# for i in range (100, 1000):
#     print(i, u3_iteratif(i+1) - u3_iteratif(i))

# Les écarts augmentent vite
# Je conjecture une Divergence vers + l'infini

#----------U4-----------

# u0 = 2
#v0 = 1
#u1 = 1.5
#v1 = 1.3333333333
#u2 = 1.415
#v2 = 1.409893993
#u3 = 1.412446997
#v3 = 1.412442382
#u4 = 1.41244469
#v4 = 1.412444689

#un+1 = un+vn / 2
#vn+1 = 2un*vn / un+vn


def uv_iteratif(n):
    u = 2
    v = 1
    for i in range (n):
        tmp = u
        u = (u+v)/2
        v = 2*tmp*v/(tmp+v)
    return u,v
    


def uv_recursif(n):
    if n == 0:
        return 2,1
    else:
        u,v = uv_recursif(n-1)
        return (u+v)/2, 2*u*v/(u+v)

# for i in range (7):
#     print(i, uv_iteratif(i), uv_recursif(i))

# for i in range (100):
#     print(i, uv_iteratif(i))


# U est strictement décroissante 
# V est strictement croissante
# Stabilisation a n = 5
# Je conjecture une convergence vers (1.414213562373095, 1.414213562373095)

# Ce sont des suites adjacentes 
# Suite adjacente = une qui monte et une qui descent
# La distece entre les 2 converges vers 0
# Théorème : u et v vers la même limite.





# PARTIE 2 QUELQUES CONVERGENCES CÉLÈBRES

#-------Nombre d'or avec suite fibonacci--------


# for i in range(2,200):
#     print (i, u3_iteratif(i+1)/ u3_iteratif(i) )

# Oscillation convergente vers 1.618033988749895
# stabilisation pour n = 40


#Voir article aproximation de PI sur wikipédia
#Voir artile sur ramanujan et ces carnets sur wikipédia
#-------Convergence vers PI-------

def bale(n):
    u = 0
    for i in range (n):
        u += 1/(i+1)**2
    u = (6*u)**(1/2)
    return u

# for i in range (100000):
#     print(i, bale(i))

# Convergence lentement vers PI


#-------Convergence vers PI (Meilleur solution)-------

def ramanujan(n):
    return math.factorial(4*n)*(1103+26390*n)/(math.factorial(n)**4*396**(4*n))

# res = 0
# for i in range (20):
#     res += ramanujan(i)
#     print(i, 9801/(2*math.sqrt(2)*res))

# Convergence vers PI
# Plus rapide que la méthode précédente
# Stabilisation atteinte à partir de n = 2




#------------Graphique de suite--------------

#graphique(u,nmin,nmax)

def graphique(u,nmin,nmax):
    tabx = range(nmin,nmax+1)
    taby = []
    for n in tabx:
        taby.append(u(n))
    plt.scatter(tabx,taby)
    plt.grid()
    plt.show()

# graphique(u1_iteratif,0,12000)
# graphique(u2_iteratif,0,20)
# graphique(u3_iteratif,0,20)
# graphique(uv_iteratif,0,20)


#----------Convergence graphique--------------

def fu(x):
    return 0.5*x+3

def fv(x):
    return math.cos(x)

def fw(x):
    return -x*x+4

def fz(x):
    return 3/(x*x +1)

def fsl(x):
    return 3.9*x*(1-x)

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

# convergence(fu,1,10,0,12,0.1)
# convergence(fu,10,10,0,12,0.1)

#Si u0 < 6 , u croissant et convergence vers 6
#Si u0 > 6 , u décroissant et convergence vers 6
#Si u0 = 6 , u constant et convergence vers 6

# convergence(fv,0.5,12,0,6,0.08)
# convergence(fv,1,12,0,6,0.08)
# convergence(fv,0.73908513,12,0,6,0.08)

#Si v0 < 0.73908513 , v  ocsilation convergente vers 0.73908513
#Si v0 > 0.73908513 , v oscilation convergente vers 0.73908513
#Si v0 = 0.73908513 , v constant et convergence vers 0.73908513

# convergence(fw,2,2,-4,4,0.5)
# convergence(fw,-3,3,-4,4,0.5)

#Si w0 < 1.56155281 , Strictement décroissant avec divergence vers -l'infini
#Si w0 > 1.56155281 , Divergence vers -l'infini et strictement décroissant à partir d'un certain rang   
#Si w0 = 1.56155281 , w contant et convergence vers 1.56155281

# convergence(fz,0,200,0,6,1)
# convergence(fz,2,100,0,6,1)
# convergence(fz,1.21341166,16,0,6,1)

# z=0
# for i in range(1000):
#     z = fz(z)
#     print(z)

#Z se rapproche d'un régime d'oscilation constante sur 2 valeurs (2.61 et 0.38)
#Si z0 = 1.21341166, z constant et convergence vers 1.21341166


# convergence(fsl,0.5,20,0,1,0.1)
convergence(fsl,0.3,2000,0,1,0.01)