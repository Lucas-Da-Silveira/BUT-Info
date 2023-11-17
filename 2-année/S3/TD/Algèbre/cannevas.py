##Modules
import sys
import pygame
import numpy as np

##Initialisation des couleurs
RED = (255, 0, 0)
GREEN = (0, 255, 0)
BLUE = (0, 0, 255)
YELLOW = (255, 255, 0)
WHITE = (255, 255, 255)
BLACK = (0, 0, 0)
PURPLE = (128, 0, 128)
ORANGE = (255, 165 ,0)
GREY = (128, 128, 128)
TURQUOISE = (64, 224, 208)

#-----------------Conditions initiales (au tout début du jeu)---------------
#------------------------------À COMPLÉTER----------------------------------

global t
t=0

   
#booleens de clavier
global keyup,keydown,keyright,keyleft,keyspace 
keyup,keydown,keyright,keyleft,keyspace=False,False,False,False,False


# booleens gestion du jeu 
start = False

##Fenêtre
pygame.init()
screen = pygame.display.set_mode((1600,1000))

#Temps
clock = pygame.time.Clock()

#-----------------Fonctions de transformation-----------------

def zoom(P, rapport):
    return P*rapport

def translation(P,x,y,z):
    for col in range(len(P[0])):
        P[0][col]+=x
        P[1][col]+=y
        P[2][col]+=z
    return P

#-----------------Cube-----------------

Pcube = np.array([[-1,-1,-1,-1,1,1,1,1],
              [-1,-1,1,1,-1,-1,1,1],
              [-1,1,1,-1,-1,1,1,-1]])

Pcube = zoom(Pcube, 50)
print(Pcube)

print("\n####################\n")

Pcube = translation(Pcube, 800, 500, 0)
print(Pcube)

print("\n####################\n")

Acube = np.array([[False]*8]*8)
print(Acube)

for pt1 in range(8):
    for pt2 in range(8):
        if(Pcube[0][pt1] - Pcube[0][pt2])**2 + (Pcube[1][pt1] - Pcube[1][pt2])**2 + (Pcube[2][pt1] - Pcube[2][pt2])**2 == 100*100:
            Acube[pt1][pt2]= True
            Acube[pt2][pt1] = True

print("\n####################\n")
print(Acube)

#-----------------Foncton dessins-----------------

def desiner(P,A):
    for ligne in range(len(P[0] - 1)):
        for colone in range(ligne+1,len(P[0])):
            if A[ligne][colone]:
                pygame.draw.line(screen, ORANGE, (P[0][ligne],P[1][ligne]), (P[0][colone],P[1][colone]))

##Boucle principale
while True:
    #Gestion du temps
    clock.tick(60)
    t+=1/60

    #Gestion des Ã©vÃ©nements
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            pygame.quit()
            sys.exit()
        if event.type == pygame.KEYDOWN:
            if event.key == pygame.K_SPACE:
              keyspace=True
            if event.key == pygame.K_UP:
              keyup=True
            if event.key == pygame.K_DOWN:
              keydown=True
            if event.key == pygame.K_RIGHT:
              keyright=True
            if event.key == pygame.K_LEFT:
              keyleft=True
        if event.type == pygame.KEYUP:
            if event.key == pygame.K_SPACE:
              keyspace=False
            if event.key == pygame.K_UP:
              keyup=False
            if event.key == pygame.K_DOWN:
              keydown=False
            if event.key == pygame.K_RIGHT:
              keyright=False
            if event.key == pygame.K_LEFT:
              keyleft=False

              
    #Clearscreen
    screen.fill(BLACK)

    #Create text and text rectangle
    font = pygame.font.Font('freesansbold.ttf', 32)
    text = font.render('To start press space', True, WHITE, BLACK)
    textRect = text.get_rect()
    textRect.center = (800, 500)


#----------------------- Jeu -----------------------------

    #Démarrage
    if not start:
        t = 0
        #Affichage du texte
        screen.blit(text, textRect)      
    
    if keyspace and not start:
        start = True

    if start:
        desiner(Pcube, Acube)

    #Mise Ã  jour de l'Ã©cran
    pygame.display.update()
