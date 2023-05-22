import matplotlib.pyplot as plt


def moyenne(li, eff=[]):
    if eff is None:
        eff = [1 for i in range (len(li))]
    somme = 0
    sommeCoef = 0
    for i in range(len(li)):
        somme += li[i]*eff[i]
        sommeCoef += eff[i]
    return somme/sommeCoef


def mediane(l):
    l.sort()
    if len(l)%2 == 0:
        return (l[len(l)//2]+l[len(l)//2-1])/2
    else:
        return l[len(l)//2]
    

def quartiles(l):
    l.sort()
    if len(l)%2 == 0:
        return (l[len(l)//4]+l[len(l)//4-1])/2, (l[3*len(l)//4]+l[3*len(l)//4-1])/2
    else:
        return l[len(l)//4], l[3*len(l)//4]

def variance(li, eff=None):
    if eff is None:
        eff = [1 for val in li]
    m = moyenne(li,eff)
    somme = 0
    for i in range(len(li)):
        somme = somme+eff[i]*(li[i]-m)**2
    return somme/sum(eff)

def ecartType(li, eff=None):
    return variance(li,eff)**0.5

def covariance(X,Y):
    mX = moyenne(X)
    mY = moyenne(Y)
    somme = 0
    for i in range(len(X)):
        somme += (X[i]-mX)*(Y[i]-mY)
    return somme/len(X)

def coeff_correlation(X,Y):
    return covariance(X,Y)/(ecartType(X)*ecartType(Y))

X = [1,3,4,5,6]
Y = [468, 500, 497, 502, 526]

def moindres_carres(X,Y):
    plt.plot(X,Y,"xb")
    a = covariance(X,Y)/variance(X)
    b = moyenne(Y)-a*moyenne(X)
    plt.plot(X,[a*x+b for x in X],"-r")
    plt.plot(moyenne(X),moyenne(Y),"xr")
    plt.show()

moindres_carres(X,Y)