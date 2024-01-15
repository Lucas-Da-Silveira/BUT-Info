cmake_minimum_required(VERSION 3.27)
project(TP_Note C)

set(CMAKE_C_STANDARD 11)

add_executable(TP_Note
        client.c
        serveur.c
)
