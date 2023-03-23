# Factorielle (n)

def factorielle(n):
    if n == 0 or n == 1:
        return 1
    res = 2;
    for i in range (3, n+1):
        res = res *i  #res *=i
    return res

# print (factorielle(0)) 
# print (factorielle(2))
# print (factorielle(3))
# print (factorielle(4))

# Absolue(x)

def absolue(x):
    if x >= 0 :
        res = x-(x*2)
        return res
    else:
        res = x-(x*2)
    return res

# print (absolue(-2))
# print (absolue(-1))
# print (absolue(0))
# print (absolue(1))
# print (absolue(2))


# U1(n)

def u1(n):
    res = 0
    for i in range (1,n+1):
        res = res +i
    return res

# print(u1(1))
# print(u1(2))
# print(u1(3))
# print(u1(4))
# print(u1(5))

# U2(n)

def u2(n):
   res = (n*(n+1))//2
   return res

# print(u2(1))
# print(u2(2))
# print(u2(3))
# print(u2(4))
# print(u2(5))

#v(n)

def v(n):
    res = 1745
    for i in range (n):
        res = 0.9972*res+2123
    return res

# print(v(1))
# print(v(2))
# print(v(3))

# for i in range(99000,100000):
#     print(v(i))

#v senmble croissante ; ralentissement : CV ?
# 10000 confirmé mais pas stabilisé
# 100 000 confirmation: CV vers 758214.2857142583

# i= 10000
# while v(i+1) != v(i):
#     i+=1
# print(i)



# n entier

for n in range (-1000000, 1000000):
    if (8*n+1)%(3*n+7) == 0:
        print(n)

#-20
#-2