# is Prime(n)

def isPrime(n):
    if n == 1:
        return False
    if n == 2:
        return True
    if n % 2 == 0:
        return False
    i = 3
    while i * i <= n:
        if n % i == 0:
            return False
        i += 2
    return True

#print(isPrime(1))
#print(isPrime(2))
#print(isPrime(15))
#print(isPrime(17))
#print(isPrime(4))
#print(isPrime(939953465063))



#ithPrime(n)

def ithPrime(n):
    if n == 1:
        return 2
    compteur = 1
    i = 3
    while compteur < n:
        if isPrime(i):
            compteur += 1
        i += 2
    return i - 2

#print(ithPrime(1))
#print(ithPrime(4))
#print(ithPrime(10))
#print(ithPrime(14))
#print(ithPrime(100000))



#decompose(n)

def decompose(n):
    tab =[]
    while n%2 == 0:
        n//=2
        tab.append(2)
    if n == 1:
        return tab
    test = 3
    while test*test <= n:
        if n%test == 0:
            tab.append(test)
            n//=test
        else:
            test+=2
    tab.append(n)
    return tab
        
#print(decompose(2))
#print(decompose(35))
#print(decompose(36))
#print(decompose(91))
#print(decompose(23))
#print(decompose(588))
#print(decompose(281688))
#print("================")
#print(decompose(1000000))
#print(decompose(1000001))
#print("================")
#print(decompose(1000000000))
#print(decompose(1000000001))
#print(decompose(1000000002))
#print(decompose(1000000003))
#print(decompose(1000000004))
#print(decompose(1000000005))
#print(decompose(1000000006))
#print(decompose(1000000007))
#print(decompose(1000000008))
#print(decompose(1000000009))
#print(decompose(1000000010))
#print("================")
#print(decompose(1000000000000))
#print(decompose(1000000000001))
#print(decompose(1000000000002))
#print(decompose(1000000000003))
#print(decompose(1000000000004))
#print(decompose(1000000000005))
#print(decompose(1000000000006))
#print(decompose(1000000000007))
#print(decompose(1000000000008))
#print(decompose(1000000000009))
#print(decompose(1000000000010))



#racine(n)

def racine(n):
    a = 1
    b = n
    while b%4==0:
        a*=2
        b//=4
    d = 3
    while d*d <= b:
        if b%(d*d)== 0:
            b//=d*d
            a*=d
        else:
            d+=2
    return a,b

#print(racine(56))
#print(racine(36))
#print(racine(13))
#print(racine(32))
#print(racine(5866080))


#pgcd(a,b)

def pgcd(a,b):
    taba= decompose(a)
    tabb= decompose(b)
    p = 1
    for i in taba:
        if i in tabb:
            p*= i
            tabb.remove(i)
    return p

#print(pgcd(12,18))

#algoEuclide(a,b)

def algoEuclide(a,b):
    if b == 0:
        return a,1,0
    else:
        d,x,y = algoEuclide(b,a%b)
        return d,y,x-(a//b)*y

print(algoEuclide(12,18))