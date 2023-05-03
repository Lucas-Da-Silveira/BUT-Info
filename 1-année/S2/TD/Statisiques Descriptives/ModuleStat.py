def moyenne(li, eff=[]):
    if eff is None:
        eff = [1 for i in range (len(li))]
    assert len(li) == len(eff)
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