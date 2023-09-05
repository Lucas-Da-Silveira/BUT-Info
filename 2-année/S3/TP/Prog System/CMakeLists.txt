cmake_minimum_required(VERSION 3.26)
project(Prog_System C)

set(CMAKE_C_STANDARD 11)

include_directories(.)
include_directories(TP1)

add_executable(Prog_System
        TP1/Ex1/HelloWorld.c
        TP1/Ex2/Boucle.c
        TP1/Ex3/Argument.c
        TP1/Ex3/Argument.c
        TP1/Ex4/Chaine.c)
