import matplotlib.pyplot as plt

#Exercice 1

Pays =['Allemaggne','Belgique','Espagne','France','Italie','Pologne','Portugal']
Habitants = [83.1, 11.4,47.3,67.8,59.0,38.4,10.3]
# plt.bar(Pays,Habitants)
# plt.show()

#Exercice 2

Moyen_déplacement = ['Voiture','Transport en commun','Pieds', 'Velo', 'Autre']
Pourcentage = [74,16,6,2,2]

explode=(0.1,0.5,0.1,0.1,0.1)
# plt.pie(Pourcentage, explode=explode,shadow=True, labels=Moyen_déplacement)
# plt.show()

#Exercice 3

Fréquence =[1,6,8,10,11,12,13,15,16]
Note =[0,2,1,3,2,4,2,2,1]

plt.bar(Fréquence,Note,  width = 0.05, color='red')
plt.show()