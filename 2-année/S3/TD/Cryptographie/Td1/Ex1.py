import math

alphabet="abcdefghijklmnopqrstuvwxyz"

#Exercice1 Chiffre de césar
def numericChar(chain,car):
  car=car.lower()
  return chain.index(car)

print(numericChar(alphabet,"b"))


def encryptShift(origMessage, key):
    encryptedMessage = ""
    if key.isalpha():
        key = numericChar(alphabet, key)
    for car in origMessage:
        if car.lower() in alphabet:
            encryptedMessage += alphabet[(numericChar(alphabet, car) + key) % 26]
        else:
            encryptedMessage += car
    return encryptedMessage

print(encryptShift("Hello World", "y"))


def decryptShift(origMessage,key):
    encryptedMessage = ""
    if key.isalpha():
        key = numericChar(alphabet, key)
    for car in origMessage:
        if car.lower() in alphabet:
            encryptedMessage += alphabet[(numericChar(alphabet, car) - key) % 26]
        else:
            encryptedMessage += car
    return encryptedMessage

print(decryptShift("fcjjm umpjb", "y"))

print("\n###############################\n")


def bruteForceAttack(origMessage, keyword = ""):
    for i in range(26):
        if decryptShift(origMessage,alphabet[i]).__contains__(keyword):
            print(decryptShift(origMessage, alphabet[i]) + " : " + alphabet[i])


print(bruteForceAttack("fcjjm umpjb", "hello"))


print("\n###############################\n")

print(encryptShift("Get me a vanilla ice cream, make it a double", alphabet[6]))
print(encryptShift("I don’t much care for Leonard Cohen", alphabet[3]))
print(encryptShift("I like root beer floats", alphabet[20]))

print("\n###############################\n")

print(decryptShift("nduzs ftq buzq oazqe", alphabet[12]))
print(decryptShift("fdhvdu qhhgv wr orvh zhljkw", alphabet[3]))
print(decryptShift("ufgihxm uly numnys", alphabet[20]))

print("\n###############################\n")

print(bruteForceAttack("gryy gurz gb tb gb nzoebfr puncry", "chapel"))
print(bruteForceAttack("wziv kyv jyfk nyve kyv tpdsrcj tirjy", "cymba"))
print(bruteForceAttack("baeeq klwosjl osk s esf ozg cfwo lgg emuz", "jim"))

print("\n###############################\n")

#Exercice2 Chiffre affine

def inverse(a,n):
    for it in range(1,n):
        if (a*it%n==1):
            return it
    return 0

def encryptAffine(origMessage, a, b):
    encryptedMessage = ""
    for car in origMessage:
        if math.gcd(a, 26) != 1:
            print("Erreur non inversible")
            return
        if car.lower() in alphabet:
            encryptedMessage += alphabet[(a * numericChar(alphabet, car) + numericChar(alphabet, b)) % 26]
        else:
            encryptedMessage += car
    return encryptedMessage

print(encryptAffine("Hello World", 5, 2))