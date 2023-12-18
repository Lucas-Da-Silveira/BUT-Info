cmake_minimum_required(VERSION 3.26)
project(En_C C)

set(CMAKE_C_STANDARD 11)

include_directories(.)

add_executable(En_C
        listdouble.h
        testlist.c)
