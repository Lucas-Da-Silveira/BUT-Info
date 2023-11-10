int main(void) {
    creer_serveur_tcp(5000, 1);

    return 0;
}



//Fermer port sudo netstat -ap | grep :5000