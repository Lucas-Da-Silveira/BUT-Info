cmake_minimum_required(VERSION 3.26)
project(TP1 C)

set(CMAKE_C_STANDARD 11)

include_directories(.)

add_executable(TP1
        client.c
        client_serveur.h
        kill.c
        server.c)
