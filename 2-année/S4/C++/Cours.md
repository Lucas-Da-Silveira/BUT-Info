## Programmes C++

### Nom de fichier : 

- extention :
    - .cpp
    - .cc
    - .C
    - .cxx

- fichier d'en-tête :
    - .h
    - .hpp
    - .hh
    - .hxx
    - .H

## Compilation 

```bash
g++ -g -Og -Wall - Wextra -o toto toto.cpp
```

## Documentation 
- [cppreference.com](https://en.cpprefernce.com/w/cpp)
- [cplusplus.com](https://cplusplus.com/refernce/)

## Exemple simple

```c++
#include <iostream>

int main(){
    std::cout << "Hello world" << std::endl;
}

//std::count = sortie standard
//<< = écrire sur un flux
// std::endl = din de ligne
// std::err = sortie d'erreur
//std::cin = entée standard
```

```c++
#include <iostream>

int main(){
    
    int n;
    std::count<<"Entrer un nombre" << std::endl
    std::cin >> n; //lecture depuis entrée standard

    for(int i = n; i >= 0; i++){
        std::cout <<"Il reste" << i << std::endl;
    } 
    std::cout << "BOOM! \n";
}
```

## Quelques spécificités

-> Espace de nommage (namespace) : std

Il est possible d'importer les déclaration d'un espace de nommage.

Par exemple :
    
- Using namespace std  -> MAIS c'est déconseillé


-> includes standars :

- iostream : entrée/sortie
- string : chaine de caractère
- vector : tableau dynamique
- ...
- et ceux de c : cstdlib , cstdio


-> Type booléan :

- bool (valeur true || false)

-> Chaîne de caractère

- type std::string

-> Tableaux

- type std::vector< T >

