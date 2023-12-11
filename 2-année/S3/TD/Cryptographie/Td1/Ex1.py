import math
import random
import string
import matplotlib.pyplot as plt

from datetime import datetime
start_time = datetime.now()

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

def encryptAffine(orig_message, a, b):
    encrypted_message = ""
    if a.isalpha() and b.isalpha():
        a = numericChar(alphabet, a)
        if math.gcd(a, 26) != 1:
            return "ERREUR: a non inversible"
        b = numericChar(alphabet, b)
        for car in orig_message:
            if car.lower() in alphabet:
                encrypted_message += alphabet[(a * numericChar(alphabet, car) + b) % 26]
            else:
                encrypted_message += car
    return encrypted_message

print(encryptAffine("Hello World", "z", "b"))


def decryptAffine(orig_message, a, b):
    decrypted_message = ""
    if a.isalpha() and b.isalpha():
        a = numericChar(alphabet, a)
        if math.gcd(a, 26) != 1:
            return "ERREUR: a non inversible"
        b = numericChar(alphabet, b)
        for car in orig_message:
            if car.lower() in alphabet:
                decrypted_message += alphabet[(inverse(a, 26) * (numericChar(alphabet, car) - b)) % 26]
            else:
                decrypted_message += car
    return decrypted_message

print(decryptAffine("uxqqn fnkqy", "z", "b"))


print("\n###############################\n")

print(encryptAffine("YES", alphabet[3], alphabet[7]))
print(decryptAffine("QXFM", alphabet[3], alphabet[7]))

print("\n###############################\n")

#Exercice3 Chiffre de Vigenère

def encryptVigenere(orig_message, key):
    encrypted_message = ""
    keyIndex = 0
    for i in range(len(orig_message)):
        if orig_message[i].isalpha():
            encrypted_message += alphabet[(numericChar(alphabet, orig_message[i]) + numericChar(alphabet, key[keyIndex % len(key)])) % 26]
            keyIndex += 1
        else:
            encrypted_message += orig_message[i]
    return encrypted_message

print(encryptVigenere("Hello World", "def"))


def decryptVigenere(orig_message, key):
    decrypted_message = ""
    KeyIndex = 0
    for i in range(len(orig_message)):
        if orig_message[i].isalpha():
            decrypted_message += alphabet[
                (numericChar(alphabet, orig_message[i]) - numericChar(alphabet, key[KeyIndex % len(key)])) % 26]
            KeyIndex += 1
    else:
        decrypted_message += orig_message[i]
    return decrypted_message

print(decryptVigenere("kiqos brvqg", "def"))


#Exercice4 Fonction analyse de sécurité

def frequenceLettres(encrypted_text):
    tab = [['a',0],['b',0],['c',0],['d',0],['e',0],['f',0],
           ['g',0],['h',0],['i',0],['j',0],['k',0],['l',0],['m',0],
           ['n',0],['o',0],['p',0],['q',0],['r',0],['s',0],
           ['t',0],['u',0],['v',0],['w',0],['x',0],['y',0],['z',0]]
    i = 0
    for lettre in alphabet:
        cpt = 0
        for char in encrypted_text:
            if char == lettre:
                cpt = cpt + 1
        tab[i] = cpt
        i += 1
    return tab



message_claire = "Ceci est mon message clair."
key = "HelloWorld"
encrypted_text = "Jotz amq vvx dyomxxn mcrcn."

letter_frequencies = frequenceLettres(encrypted_text)

plt.bar(range(26), letter_frequencies)
plt.xlabel('Lettres')
plt.ylabel('Fréquence')
plt.xticks(range(26), alphabet)
plt.title('Fréquence des lettres dans le texte crypté')
plt.show()


#exercice 5 test de performance
