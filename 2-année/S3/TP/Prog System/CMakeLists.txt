cmake_minimum_required(VERSION 3.26)
project(Prog_System C)

set(CMAKE_C_STANDARD 11)

include_directories(.)
include_directories(TP1)
include_directories(TP2)
include_directories(TP3)
include_directories(TP4)
include_directories(TP5)


add_executable(Prog_System
#        TP1/Ex1/HelloWorld.c
#        TP1/Ex2/Boucle.c
#        TP1/Ex3/Argument.c
#        TP1/Ex3/Argument.c
#        TP1/Ex4/Chaine.c
#        TP2/ex1/CopieFichier.c
#        TP3/TP3.c
#        TP4/TP4.c
        TP5/TP5.c
)
