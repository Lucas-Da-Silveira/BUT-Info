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

#---------------- fonctions de transformation ----------------#
def zoom(P, rapport, x, y, z):
    P = translation(P, -x, -y, -z)
    P *= rapport
    P = translation(P, x, y, z)
    return P

def reflexionOz(P,x,y,z):
    P = translation(P, -x, -y, -z)
    S = R = np.array([[-1, 0, 0],
                        [0, -1, 0],
                        [0, 0, 1]])
    P = np.dot(R, P)
    P = translation(P, x, y, z)
    return P

def translation(P, x, y, z):
    for colone in range(len(P[0])):
        P[0][colone] += x
        P[1][colone] += y
        P[2][colone] += z
    return P

def rotx(P, angle, x, y, z):
    c = np.cos(np.radians(angle))
    s = np.sin(np.radians(angle))
    R = np.array([[1, 0, 0],
                  [0, c, -s],
                  [0, s, c]])
    P = translation(P, -x, -y, -z)
    P = np.dot(R, P)
    P = translation(P, x, y, z)
    return P

def roty(P, angle, x, y, z):
    c = np.cos(np.radians(angle))
    s = np.sin(np.radians(angle))
    R = np.array([[c, 0, s],
                  [0, 1, 0],
                  [-s, 0, c]])
    P = translation(P, -x, -y, -z)
    P = np.dot(R, P)
    P = translation(P, x, y, z)
    return P

def rotz(P, angle, x, y, z):
    c = np.cos(np.radians(angle))
    s = np.sin(np.radians(angle))
    R = np.array([[c, -s, 0],
                  [s, c, 0],
                  [0, 0, 1]])
    P = translation(P, -x, -y, -z)
    P = np.dot(R, P)
    P = translation(P, x, y, z)
    return P


#----------------changmement de base----------------#

def rotation(P,axe, plan1,plan2,x,y,z,angle):
    c= np.cos(np.radians(angle))
    s= np.sin(np.radians(angle))
    mbb = np.array([[1,0,0],
                    [0,c,-s],
                    [0,s,c]])
    pos = np.array([[axe[0],plan1[0],plan2[0]],
                    [axe[1],plan1[1],plan2[1]],
                    [axe[2],plan1[2],plan2[2]]])
    mcan = np.dot(np.dot(pos,mbb),np.linalg.inv(pos))
    P = translation(P, -x, -y, -z)
    P = np.dot(mcan, P)
    P = translation(P, x, y, z)
    return P

#---------------- fonction de dessin ----------------#
def dessinerCube(P, A):
    for ligne in range(len(P[0]) - 1):
        for colone in range(ligne+1, len(P[0])):
            if A[ligne][colone]:
                pygame.draw.line(screen, ORANGE, (P[0][ligne],P[1][ligne]), (P[0][colone],P[1][colone]), 2)

def dessinerIco(P, A):
    for ligne in range(len(P[0]) - 1):
        for colone in range(ligne+1, len(P[0])):
            if A[ligne][colone]:
                pygame.draw.line(screen, BLUE, (P[0][ligne],P[1][ligne]), (P[0][colone],P[1][colone]), 2)

def dessinerCol(P, A):
    for ligne in range(len(P[0]) - 1):
        for colone in range(ligne+1, len(P[0])):
            if A[ligne][colone]:
                pygame.draw.line(screen, RED, (P[0][ligne],P[1][ligne]), (P[0][colone],P[1][colone]), 2)

#------------------ CUBE ----------------#

Pcube =  np.array([[-1,-1,-1,-1,1,1,1,1],
                  [-1,-1,1,1,-1,-1,1,1],
                  [-1,1,-1,1,-1,1,-1,1]])

Acube = np.array([[False]*8]*8)


Pcube = zoom(Pcube, 50, 0, 0, 0)
Pcube = translation(Pcube, 800, 500, 0)
Pcube = rotx(Pcube, 30, 800, 500, 0)
Pcube = roty(Pcube, 20, 800, 500, 0)



for pt1 in range(8):
    for pt2 in range(8):
        if np.sqrt((Pcube[0][pt1]-Pcube[0][pt2])**2 + (Pcube[1][pt1]-Pcube[1][pt2])**2 + (Pcube[2][pt1]-Pcube[2][pt2])**2) <= 101:
            Acube[pt1][pt2] = True
            Acube[pt2][pt1] = True

#------------------ Icosaèdre ----------------#

phi=(1+np.sqrt(5))/2
Pico = np.array([[0,phi, phi, -phi, -phi, -1, 1, -1, 1, 0, 0, 0, 0],
                 [0,-1, 1, -1, 1, 0, 0, 0, 0, phi, phi, -phi, -phi],
                 [0,0, 0, 0, 0, phi, phi, -phi, -phi, -1, 1, -1, 1]])

Pico = zoom(Pico, 25, 0, 0, 0)
Pico = translation(Pico, 800, 500, 600)

Aico = np.array([[False]*13]*13)
for pt1 in range(13):
    for pt2 in range(13):
        if np.sqrt((Pico[0][pt1]-Pico[0][pt2])**2 + (Pico[1][pt1]-Pico[1][pt2])**2 + (Pico[2][pt1]-Pico[2][pt2])**2) <= 51:
            Aico[pt1][pt2] = True
            Aico[pt2][pt1] = True


#------------------ Coline ----------------#

Pcol =  np.array([[0]*441]*3)
c=0
for x in range(-500, 550, 50):
    for z in range(-500, 550, 50):
        y= 0.002*x**2+0.002*z**2
        Pcol[0][c]=x
        Pcol[1][c]=y
        Pcol[2][c]=z
        c+=1

print(Pcol)



Pcol = translation(Pcol, 800, 0, 0)


Acol = np.array([[False]*441]*441)

for pt1 in range(441):
    for pt2 in range(441):
        if np.sqrt((Pcol[0][pt1]-Pcol[0][pt2])**2 + (Pcol[1][pt1]-Pcol[1][pt2])**2 + (Pcol[2][pt1]-Pcol[2][pt2])**2) <= 101:
            Acol[pt1][pt2] = True
            Acol[pt2][pt1] = True

print(Acol)

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
        #dessinerCube(Pcube, Acube)
        Pcube = rotx(Pcube, 1, 800, 500, 0)
        Pcube = roty(Pcube, 7, 800, 500, 0)
        Pcube = rotz(Pcube, 3, 800, 500, 0)

        # if t < 2:
        #     r = 0.01
        # elif t<4:
        #     r = 0.09
        # else:
        #     t=0

        #Pcube = zoom(Pcube, 1+r*np.cos(t),800, 500, 0)
        Pcube= zoom(Pcube, 1+0.02*np.cos(t),800, 500, 0)

        dessinerIco(Pico, Aico)
        #Pico = rotx(Pico, -1, 800, 500, 0)
        #Pico = roty(Pico, -2, 800, 500, 0)
        #Pico = rotz(Pico, -3, 800, 500, 0)
        #Pico= zoom(Pico, 1+0.04*np.cos(t),800, 500, 0)

        Pico = zoom(Pico,1+0.000001*Pico[0][2],Pico[0][0], Pico[0][1], Pico[0][2])
        Pico = rotation(Pico,(0,1,0),(1,0,0),(0,0,1), 800, 500, 0, 1)
        Pico = rotation(Pico,(1,-10,0),(10,1,0),(0,0,10), 800, 500, 0, 1)



        dessinerCol(Pcol, Acol)
        #Pcol = roty(Pcol, 1, 800, 0, 0)
        Pcol = rotation(Pcol,(1,-10,0),(10,1,0),(0,0,10), 800, 500, 0, 1)
        Pcol_mir = reflexionOz(Pcol, 0, 0, 0)
        Pcol_mir = translation(Pcol_mir, 1600, 1000, 0)
        dessinerCube(Pcol_mir, Acol)

    #Mise Ã  jour de l'Ã©cran
    pygame.display.update()