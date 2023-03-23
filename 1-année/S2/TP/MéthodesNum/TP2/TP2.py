import numpy as np
import math
import matplotlib.pyplot as plt

#----------1) Fonction----------

def f(x):
    return (x**5)-(3*x**4)+(2*x**3)+(5*x**2)-(7*x)+2

def g(x):
    return x*math.sin(x)

def h(x):
    return math.sin(1/x)

def z(x):
    return (math.exp((-((x-5.456454)**2)/2))+(math.exp(-((x-58.34523)**2)/2)))

def test(x):
    return (x**2)-2

def affiche(f,xmin,xmax,pas):
    tabx = np.arange(xmin,xmax,pas)
    taby = []
    for x in tabx:
        taby.append(f(x))
    plt.plot(tabx,taby)
    plt.grid()
    plt.show()

# affiche(f,-10,11,0.01)
# affiche(f,-1.5,1.5,0.01)

# affiche(g,-10,11,0.01)
# affiche(g,-2,2,0.01)
# affiche(g,-20,20,0.01)
# affiche(g,4,11,0.01)

# affiche(h,-100,100,0.01)
# affiche(h,-0.05,0.05,0.000001)

# affiche(z,-10,11,0.01)
# affiche(z,-1,70,0.01)

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
    
# print(zero(test,0,2,0.000000000000001))
#15 décimal de juste 

# print(zero(f,-1.5,-1,0.000000000000001))
# print(zero(f,0.2,0.6,0.000000000000001))
# print(zero(f,0.9,1.1,0.000000000000001))

def cube(x):
    return x**3

def derive(f,x,precision):
    return (f(x+precision)-f(x))/precision

# print(derive(cube,2,0.00000001))

def test1(x):
    return (-x*x)+4

def max(f,xmin,xmax):
    def fprime(x):
        return derive(f,x,0.00000001)
    x0 = zero(fprime,xmin,xmax,0.000000000000001)
    y0 = f(x0)
    return x0,y0

print(max(test1,-1,1))
#max local = (0.0 , 4.0)

print(max(f,-1.5,0.5))
#max local = (-0.87, 8.34)

print(max(g,5,10))
#max local = (7.97 , 7.91)