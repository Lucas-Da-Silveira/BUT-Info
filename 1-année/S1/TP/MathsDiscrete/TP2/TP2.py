
import sys
sys.setrecursionlimit(150000)
#===========================================
#PARTI 1

#Dernier chiffre (n)

def dernierChiffre(n):
    n = n%10
    return n
#print(dernierChiffre(114))
#print(dernierChiffre(12))
#print(dernierChiffre(13))
#print(dernierChiffre(14))
#print(dernierChiffre(15))


#deux dernier chiffre(n)

def deuxDernierChiffre(n):
    if n<10 :
        return False
    else :
        n = n % 100
        return n   
#print(deuxDernierChiffre(10))
#print(deuxDernierChiffre(11))
#print(deuxDernierChiffre(200))
#print(deuxDernierChiffre(543))
#print(deuxDernierChiffre(2756))


#dernier chiffre (n,nbre)

def dernierChiffres(n,nbre):
        n = n % 10**nbre
        return n
#print(deuxDernierChiffres(111 , 1))
#print(deuxDernierChiffres(3453 , 1))
#print(dernierChiffres(276553 , 4))


#nombre CHiffre (n)

def nombreChiffre(n):
    c = 0
    while(n != 0):
        n = n // 10
        c +=1
    return c
    #return len(str(n))
#print(nombreChiffre(10))
#print(nombreChiffre(100))
#print(nombreChiffre(1000))
#print(nombreChiffre(10000))
#print(nombreChiffre(100000))


#premier Chiffre(n)

def premierChiffre(n):
    while (n>=10):
        n = n //10
    return n
#print(premierChiffre(11))
#print(premierChiffre(300))
#print(premierChiffre(5025))
#print(premierChiffre(66423))


#premier Chiffre(n , nbre)

def premierChiffres(n , nbre):
    while (n>=10**nbre):
        n = n // 10
    return n
#print(premierChiffres(23456 , 3))
#print(premierChiffres(111 , 2))
#print(premierChiffres(3453 , 2))
#print(premierChiffres(276553 , 4))


#chiffres(n,debut,fin)

def chiffres(n,début,fin):
    n = premierChiffres(n,fin)
    n = dernierChiffres(n,((fin-début)+1))
    return n
#print(chiffres(456345234, 3, 6))
#print(chiffres(2473981321532 , 5 , 11))



#===========================================
#PARTI 2


#quotient (a,b)

def quotien(a,b):
    if b==0:
        print ("erreur : div par 0")
        return
    
    q = 0
    while(a >= b):
        q+=1
        a-=b   
    return q
#print(quotien(4,2))
#print(quotien(100,4))
#print(quotien(867,3))


#reste (a,b)

def reste(a,b):
    if b==0:
        print ("erreur : div par 0")
        return
    while(a >= b):
        a-=b  
    return a
#print(reste(4,2))
#print(reste(100,4))
#print(reste(867,3))



#div(a,b)

def div(a,b):
    if b==0:
        print ("erreur : div par 0")
        return
    
    q = 0
    while(a >= b):
        q+=1
        a-=b   
    return q,a
#print(div(4,2))
#print(div(143,4))
#print(div(867,3))



#div2(a,b)

def div2(a,b):
    if b == 0:
        print ("erreur : div par 0")
        return

    q,r = div(abs(a),abs(b))

    if r >0:
        if a >= 0 and b > 0:
            return q,r
        
        if a >= 0 and b < 0:
            return -q,r

        if a < 0 and b > 0:
            return -q-1 , b-r
        if a< 0 and b < 0:
            return q+1, -b-r
        
    else: #r== 0
        if a * b >=0:
            return q,0
        else:
            return -q,0

#print(div2(41,5))
#print(div2(41,-5))
#print(div2(-41,5))
#print(div2(-41,-5))


#=========================
# Partie 3

#(a**b)%c

#print((1037**2000000)%5242)


#reste(1037**b,5242)
#print(reste(1037**3, 5242))


#Puissance (a,b,c)

def puissance(a,b,c):
    res=1
    for i in range(b):
        res = (res*a)%c
    return res

#print(puissance(7,1200000,10))


#puissance2(a,b,c)



def puissance2(a,b,c):
    if b == 1:
        return 1
    
    if b == 0:
        return a
        
    if b%2 == 0:
        res = puissance2(a,b//2,c)
        return (res*res)%c

    if b%2 == 1:
        res = puissance2(a,b//2,c)
    return ((res*res)*a)%c

#print(puissance2(7,10**6500,10))



#puissance3 (a,b,c)

def puissance3(a,b,c):
    a=a%c
    tab=[]
    res=1
    while res not in tab:
        tab.append(res)
        res=(res*a)%c
    deb = tab.index(res)
    longcycle = len(tab) - deb
    if b<deb :
        return tab[b]
    else:
        b = b%longcycle
    i = deb
    while b != i%longcycle:
        i+=1
    return tab[i]


#print(puissance3(1037,10,5242))
#print(puissance3(1037,10,5243))
#print(puissance3(1037,10,5244))
#print(puissance3(1037,10,5245))
#print(puissance3(1037,10,5246))
#print(puissance3(1037,10,5247))

print(puissance3(1037,10**7000000+284589,5242))