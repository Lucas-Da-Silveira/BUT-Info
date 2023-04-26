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


def mediane()
